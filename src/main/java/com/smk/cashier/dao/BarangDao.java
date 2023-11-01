package com.smk.cashier.dao;

import com.smk.cashier.model.Barang;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

public class BarangDao implements Dao<Barang, Integer>{
    private final Optional<Connection> conn;

    public BarangDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Barang> get(int id) {
        return conn.flatMap(c -> {
            Optional<Barang> barang = Optional.empty();

            String query = "SELECT * FROM barang WHERE id = ?;";
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String kode = rs.getString("kode");
                    String nama = rs.getString("nama");
                    int harga = rs.getInt("harga");

                    Barang result = new Barang();
                    result.setKode(kode);
                    result.setNama(nama);
                    result.setHarga(harga);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return barang;
        });
    }

    @Override
    public Collection<Barang> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Barang barang) {
        Barang nonNullB = Objects.requireNonNull(barang);
        String query = "INSERT INTO barang(kode, nama, harga, last_modified, updated_by, created_by, date_created) "+
                "VALUES(?, ?, ?, ?, ?, ?, ?);";

        return conn.flatMap(c -> {
            Optional<Integer> generatedID = Optional.empty();
            try {
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, barang.getKode());
                ps.setString(2, barang.getNama());
                ps.setInt(3, barang.getHarga());
                ps.setDate(4, new Date(barang.getLast_modified().getTime()));
                ps.setString(5, barang.getUpdatedBy());
                ps.setString(6, barang.getCreatedBy());
                ps.setDate(7, new Date(barang.getDate_created().getTime()));

                int numberOfInsertedRows = ps.executeUpdate();
                if (numberOfInsertedRows > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        generatedID = Optional.of(rs.getInt(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return generatedID;
        });
    }

    @Override
    public void update(Barang barang) {
        Barang nonNullBarang = Objects.requireNonNull(barang);
        String query = "UPDATE barang SET harga = ?, nama = ?, last_modified = ?, updated_by = ? WHERE kode = ?;";
        conn.ifPresent(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(query);

                ps.setInt(1, barang.getHarga());
                ps.setString(2, barang.getNama());
                ps.setDate(3, new Date(new java.util.Date().getTime()));
                ps.setString(4, "Jessie Pinkman");
                ps.setString(5, barang.getKode());

                int affectedRows = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void delete(Barang barang) {
        Barang nonNullBarang = Objects.requireNonNull(barang);
        String query = "DELETE FROM barang WHERE kode = ?";
        conn.ifPresent(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, barang.getKode());
                int affectedRows = ps.executeUpdate();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Collection<Barang> search(String keyword) {
        Collection<Barang> returnList = new LinkedList<>();

        String nonNullString = Objects.requireNonNull(keyword);
        String query = "SELECT * FROM barang WHERE kode LIKE CONCAT('%',?,'%')" +
                        "OR nama LIKE CONCAT('%', ?, '%');";
        conn.ifPresent(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, keyword);
                ps.setString(2, keyword);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String kode = rs.getString("kode");
                    String nama = rs.getString("nama");
                    int harga = rs.getInt("harga");
//                    String createdBy = rs.getString("created_by");
//                    String updatedBy = rs.getString("updated_by");
//                    Date createdDate = rs.getDate("date_created");
//                    Date lastModified = rs.getDate("last_modified");

                    Barang bRes = new Barang();
                    bRes.setKode(kode);
                    bRes.setNama(nama);
                    bRes.setHarga(harga);
//                    bRes.setCreatedBy(createdBy);
//                    bRes.setUpdatedBy(updatedBy);
//                    bRes.setDate_created(createdDate);
//                    bRes.setLast_modified(lastModified);

                    returnList.add(bRes);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return returnList;
    }
}
