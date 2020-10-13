package app.entities;

public class Item2080 {

    private int item;

    private String date;

    private int status_code;

    private String status;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Item2080(int item, String date, int status_code) {
        this.item = item;
        this.date = date;
        this.status_code = status_code;
    }
}
