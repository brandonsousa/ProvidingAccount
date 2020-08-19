package br.com.seasyc.providingaccount.models.responses;

public class Deleted {
    private boolean deleted;
    private String data;

    public Deleted() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
