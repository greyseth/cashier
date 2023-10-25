package com.smk.cashier.service;

import com.smk.cashier.dao.StockDao;
import com.smk.cashier.dao.StockDao;
import com.smk.cashier.dao.StockDao;
import com.smk.cashier.model.Stock;
import com.smk.cashier.model.Stock;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {
    @Test
    @Order(1)
    void saveToDB() {
        StockDao dao = new StockDao();

        Stock laptop = new Stock();
        laptop.setKode("B001");
        laptop.setStock(10);
        laptop.setCreatedDate(new Date());
        laptop.setLastModified(new Date());
        dao.save(laptop);

        Stock mouse = new Stock();
        mouse.setKode("B002");
        mouse.setStock(15);
        mouse.setCreatedDate(new Date());
        mouse.setLastModified(new Date());
        dao.save(mouse);

        Stock gamingLaptop = new Stock();
        laptop.setKode("B003");
        laptop.setStock(5);
        laptop.setCreatedDate(new Date());
        laptop.setLastModified(new Date());
        dao.save(laptop);
    }
    
    @Test
    @Order(2)
    void getById() {
        StockDao dao = new StockDao();
        Optional<Stock> s1 = dao.get(1);
        s1.ifPresent(new Consumer<Stock>() {
            @Override
            public void accept(Stock stock) {
                assertEquals("B001", stock.getKode());
                assertEquals(10, stock.getStock());
            }
        });

        Optional<Stock> s2 = dao.get(2);
        s2.ifPresent(new Consumer<Stock>() {
            @Override
            public void accept(Stock stock) {
                assertEquals("B002", stock.getKode());
                assertEquals(15, stock.getStock());
            }
        });

        Optional<Stock> s3 = dao.get(3);
        s3.ifPresent(new Consumer<Stock>() {
            @Override
            public void accept(Stock stock) {
                assertEquals("B003", stock.getKode());
                assertEquals(5, stock.getStock());
            }
        });
    }

}