package com.smk.cashier.service;

import com.smk.cashier.model.Barang;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

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
}