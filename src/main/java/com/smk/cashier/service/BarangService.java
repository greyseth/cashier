package com.smk.cashier.service;

import com.smk.cashier.model.Barang;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BarangService {
    FileReader bReader;
    FileWriter bWriter;

    List<Barang> bList = new LinkedList<>();
    private static BarangService bService = null;

    private BarangService() {
        try {
            bWriter = new FileWriter("barang.txt");
            bReader = new FileReader("barang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized BarangService getInstance() {
        if (bService == null) bService = new BarangService();

        return bService;
    }

    private void readFile() {
        BufferedReader bufferedReader = new BufferedReader(bReader);
        List<String> strList = bufferedReader.lines().toList();

        //Overwrites bList into empty list
        bList = new LinkedList<>();
        for (String str :
                strList) {
            bList.add(parseLineToBarang(str));
        }
    }

    private void writeFile() {
        try {
            bWriter = new FileWriter("barang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(bWriter);
        for (int i = 0; i < bList.size(); i++) {
            Barang barang = bList.get(i);

            StringBuilder sb = new StringBuilder();
            sb.append(barang.getKode());
            sb.append("|");
            sb.append(barang.getNama());
            sb.append("|");
            sb.append(barang.getHarga());

            try {
                bufferedWriter.write(sb.toString());

                if (i < bList.size() - 1) bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Barang parseLineToBarang(String str) {
        StringTokenizer st = new StringTokenizer(str, "|");

        int id = 0;
        Barang barang = new Barang();
        while(st.hasMoreElements()) {
            if (id == 0) barang.setKode(st.nextToken());
            else if (id == 1) barang.setNama(st.nextToken());
            else if (id == 2) barang.setHarga(Integer.parseInt(st.nextToken()));

            id++;
        }

        return barang;
    }

    public List<Barang> getBarangList() {
        readFile();
        return bList;
    }

    public void addBarang(Barang barang) {
        bList.add(barang);
        writeFile();
    }

    public List<Barang> findByName(String name) {
        return bList.stream().filter(barang -> barang.getNama().startsWith(name)).toList();
    }
}
