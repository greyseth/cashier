package com.smk.cashier.dao;

import com.smk.cashier.model.Barang;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class BarangDao implements Dao<Barang, Integer>{
    private final Optional<Connection> conn;

    public BarangDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Barang> get(int id) {
        return Optional.empty();
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

    }

    @Override
    public void delete(Barang barang) {

    }
}
