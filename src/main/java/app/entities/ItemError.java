package app.entities;

public class ItemError {

    private String item;
    private String error;
    private int status;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ItemError(String item, String error, int status) {
        this.item = item;
        this.error = error;
        this.status = status;
    }
}
