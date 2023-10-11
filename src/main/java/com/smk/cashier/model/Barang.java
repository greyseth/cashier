package com.smk.cashier.model;

import java.util.Date;

public class Barang extends Model {
    private String kode;
    private String nama;
    private int harga;
    private String createdBy;
    private String updatedBy;
    private Date date_created;
    private Date last_modified;

    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
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

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "kode='" + kode + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", date_created='" + date_created + '\'' +
                ", last_modified='" + last_modified + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
