package app.services;

import app.entities.Item2080;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class BlenderJDBCService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.dataSource.password}")
    private String password;

    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(BlenderJDBCService.class);

    private void setConnection(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.microsoft.sqlserver.jdbc.SQLServerDriver.class);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Item2080 get2080(int item, String store) throws RuntimeException{
        setConnection();
        List<Item2080> resultItems = jdbcTemplate.query("SELECT NUM_ART, DAT_RLVSTK , s" + store +
                        " FROM DataMart_View.dbo.VA_Date_Item_2080_Auto vdia " +
                        "WHERE NUM_ART = ? AND DAT_RLVSTK = (SELECT MAX(DAT_RLVSTK) " +
                        "FROM DataMart_View.dbo.VA_Date_Item_2080_Auto vdia2)", new Object[]{item},

                new RowMapper<Item2080>() {
                    @Override
                    public Item2080 mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Item2080(rs.getInt(1), rs.getDate(2), rs.getInt(3));
                    }
                }

        );
        if (resultItems.size() == 1) {
            return resultItems.get(0);
        }

        return null;

    }

}
