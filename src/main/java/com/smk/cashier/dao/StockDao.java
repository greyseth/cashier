package com.smk.cashier.dao;

import com.smk.cashier.model.Stock;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class StockDao implements Dao<Stock, Integer> {
    private final Optional<Connection> conn;

    public StockDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Stock> get(int id) {
        return conn.flatMap(c -> {
            Optional<Stock> stock = Optional.empty();

            String query = "SELECT * FROM stock WHERE id = ?;";
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int stockId = rs.getInt("id");
                    String kode = rs.getString("kode");
                    int stockNum = rs.getInt("stock");

                    Stock result = new Stock();
                    result.setId(stockId);
                    result.setKode(kode);
                    result.setStock(stockNum);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return stock;
        });
    }

    @Override
    public Collection<Stock> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Stock stock) {
        Stock nonNullB = Objects.requireNonNull(stock);
        String query = "INSERT INTO stock(kode, stock, last_modified, updated_by, created_by, date_created) "+
                "VALUES(?, ?, ?, ?, ?, ?, ?);";

        return conn.flatMap(c -> {
            Optional<Integer> generatedID = Optional.empty();
            try {
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, stock.getKode());
                ps.setInt(2, stock.getStock());
                ps.setDate(4, new Date(stock.getLastModified().getTime()));
                ps.setString(5, stock.getUpdatedBy());
                ps.setString(6, stock.getCreatedBy());
                ps.setDate(7, new Date(stock.getCreatedDate().getTime()));

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
    public void update(Stock stock) {

    }

    @Override
    public void delete(Stock stock) {

    }

    @Override
    public Collection<Stock> search(String keyword) {
        return null;
    }

}
