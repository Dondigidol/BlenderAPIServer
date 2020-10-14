package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Item2080 {

    private int item;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    private int status_code;

    private String status;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public Item2080(int item, Date date, int status_code) {
        this.item = item;
        this.date = date;
        this.status_code = status_code;
    }
}
