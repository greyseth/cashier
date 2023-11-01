package com.smk.cashier.service;

import com.smk.cashier.dao.BarangDao;
import com.smk.cashier.model.Barang;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BarangServiceTest {

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> bList = BarangService.getInstance().getBarangList();
        assertEquals(bList.size(), 3);
    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKode("B001");
        laptop.setNama("Lappy toppy");
        laptop.setHarga(10000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKode("B002");
        mouse.setNama("Mousey mouse");
        mouse.setHarga(120000);
        BarangService.getInstance().addBarang(mouse);

        Barang pc = new Barang();
        pc.setKode("B003");
        pc.setNama("Lappy geming edition");
        pc.setHarga(69420);
        BarangService.getInstance().addBarang(pc);
    }

    @Test
    @Order(3)
    void findByName() {
        List<Barang> res = BarangService.getInstance().findByName("Lappy");
        assertEquals(res.size(), 2);
    }

    @Test
    @Order(4)
    void saveToDB() {
        BarangDao dao = new BarangDao();

        Barang laptop = new Barang();
        laptop.setKode("B001");
        laptop.setNama("Lappy toppy");
        laptop.setHarga(10000000);
        laptop.setDate_created(new Date());
        laptop.setLast_modified(new Date());
        dao.save(laptop);

        Barang mouse = new Barang();
        mouse.setKode("B002");
        mouse.setNama("Mousey mouse");
        mouse.setHarga(120000);
        mouse.setDate_created(new Date());
        mouse.setLast_modified(new Date());
        dao.save(mouse);

        Barang pc = new Barang();
        pc.setKode("B003");
        pc.setNama("Lappy geming edition");
        pc.setHarga(69420);
        pc.setDate_created(new Date());
        pc.setLast_modified(new Date());
        dao.save(pc);
    }

    @Test
    @Order(5)
    void getById() {
        BarangDao dao = new BarangDao();
        Optional<Barang> b1 = dao.get(1);
        b1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Lappy Toppy", barang.getNama());
                assertEquals("B001", barang.getKode());
            }
        });

        Optional<Barang> b2 = dao.get(2);
        b2.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Mousey Mouse", barang.getNama());
                assertEquals("B002", barang.getKode());
            }
        });

        Optional<Barang> b3 = dao.get(3);
        b3.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Lappy geming edition", barang.getNama());
                assertEquals("B003", barang.getKode());
            }
        });
    }

    @Test
    @Order(6)
    void UpdateBarang() {
        BarangDao dao = new BarangDao();

        Barang bUpd = new Barang();
        bUpd.setKode("B001");
        bUpd.setNama("Lap");
        bUpd.setHarga(10000);

        dao.update(bUpd);

        Optional<Barang> b1 = dao.get(1);
        b1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Lap", barang.getNama());
                assertEquals(10000, barang.getHarga());
                assertEquals("B001", barang.getKode());
            }
        });
    }

    @Test
    @Order(7)
    void deleteBarang() {
        BarangDao dao = new BarangDao();

        Barang b = new Barang();
        b.setKode("B001");

        dao.delete(b);

        Optional<Barang> b1 = dao.get(1);
        assertEquals(b1.isPresent(), false);
    }

    @Test
    @Order(8)
    void search() {
        BarangDao dao = new BarangDao();

//        Barang newB1 = new Barang();
//        newB1.setKode("B001");
//        newB1.setNama("Bluetooth keyboard");
//        newB1.setHarga(120000);
//        newB1.setLast_modified(new Date());
//        newB1.setDate_created(new Date());
//        dao.save(newB1);
//
//        Barang newB2 = new Barang();
//        newB2.setKode("B004");
//        newB2.setNama("Bluetooth speaker");
//        newB2.setHarga(50000);
//        newB2.setLast_modified(new Date());
//        newB2.setDate_created(new Date());
//        dao.save(newB2);
//
//        Barang newB3 = new Barang();
//        newB3.setKode("B005");
//        newB3.setNama("Laptop new edition");
//        newB3.setHarga(2000000);
//        newB3.setLast_modified(new Date());
//        newB3.setDate_created(new Date());
//        dao.save(newB3);

        Collection<Barang> searched = dao.search("Bluetooth");
        assertEquals(searched.size(), 2);
    }
}