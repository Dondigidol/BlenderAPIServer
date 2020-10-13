package app.entities;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Subselect("SELECT ITEM, MAX(UDA_DATE) as uda_date " +
        "FROM dbo.RMS_V_T_UDA_ITEM_DATE rvtuid " +
        "GROUP BY ITEM ")
public class ItemAvs {

    @Id
    private String item;
    @DateTimeFormat
    private String uda_date;

    public void setItem(String item){
        this.item = item;
    }

    public void setUda_date(String uda_date){
        this.uda_date = uda_date;
    }

    public String getItem(){
        return this.item;
    }

    public String getUda_date(){
        return this.uda_date;
    }
}
