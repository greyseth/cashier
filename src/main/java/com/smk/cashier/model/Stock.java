package com.smk.cashier.model;

import java.util.Date;

public class Stock extends Model {
    private int id;
    private String kode;
    private int stock;

    private Date createdDate;
    private Date lastModified;
    private String createdBy;
    private String updatedBy;

    public int getId() {
        return id;
    }
    public void setId(int newId) {
        this.id = newId;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String newKode) {
        this.kode = newKode;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int newStock) {
        this.stock = newStock;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", kode='" + kode + '\'' +
                ", stock=" + stock +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
