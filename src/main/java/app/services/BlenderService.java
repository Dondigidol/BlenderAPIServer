package app.services;

import app.entities.Item2080;
import app.entities.ItemAvs;
import app.repos.RepositoryAvs;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlenderService {

    @Autowired
    private RepositoryAvs repositoryAvs;

    @Autowired
    private BlenderJDBCService blenderJDBCService;

    public ItemAvs getAvs(String item){
        Optional<ItemAvs> opt_item = repositoryAvs.findByItem(item);
        if (opt_item.isPresent()) return opt_item.get();
        return  null;
    }

    public Item2080 get2080(int item, String store){
        try {
            return blenderJDBCService.get2080(item, store);
        } catch (RuntimeException e){
            return null;
        }

    }
}
