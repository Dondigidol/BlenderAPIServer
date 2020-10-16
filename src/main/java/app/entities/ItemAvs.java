package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Immutable
@Subselect("SELECT ITEM, UDA_DATE, MAX(LAST_UPDATE_DATETIME) as last_update " +
        "FROM DataLake_View.dbo.RMS_V_T_UDA_ITEM_DATE rvtuid " +
        "WHERE UDA_ID = 6 " +
        "GROUP BY ITEM, UDA_DATE ")
public class ItemAvs {

    @Id
    private String item;
    @DateTimeFormat
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "uda_date")
    private Date avsDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "last_update")
    private Date updateDate;



    public void setItem(String item){
        this.item = item;
    }

    public String getItem(){
        return this.item;
    }

    public Date getAvsDate() {
        return avsDate;
    }

    public void setAvsDate(Date avsDate) {
        this.avsDate = avsDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
