package com.smk.cashier.service;

import com.smk.cashier.model.Barang;
import com.smk.cashier.model.Stock;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class StockService {
    FileReader sReader;
    FileWriter sWriter;

    List<Stock> sList = new LinkedList<>();
    private static StockService sService = null;

    private StockService() {
        try {
            sWriter = new FileWriter("stock.txt");
            sReader = new FileReader("stock.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized StockService getInstance() {
        if (sService == null) sService = new StockService();

        return sService;
    }

    private void readFile() {
        BufferedReader bufferedReader = new BufferedReader(sReader);
        List<String> strList = bufferedReader.lines().toList();

        //Overwrites sList into empty list
        sList = new LinkedList<>();
        for (String str :
                strList) {
            sList.add(parseLineToStock(str));
        }
    }

    private void writeFile() {
        try {
            sWriter = new FileWriter("stock.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(sWriter);
        for (int i = 0; i < sList.size(); i++) {
            Stock stock = sList.get(i);

            StringBuilder sb = new StringBuilder();
            sb.append(stock.getId());
            sb.append("|");
            sb.append(stock.getKode());
            sb.append("|");
            sb.append(stock.getStock());

            try {
                bufferedWriter.write(sb.toString());

                if (i < sList.size() - 1) bufferedWriter.newLine();
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

    private Stock parseLineToStock(String str) {
        StringTokenizer st = new StringTokenizer(str, "|");

        int id = 0;
        Stock stock = new Stock();
        while(st.hasMoreElements()) {
            if (id == 0) stock.setId(Integer.parseInt(st.nextToken()));
            else if (id == 1) stock.setKode(st.nextToken());
            else if (id == 2) stock.setStock(Integer.parseInt(st.nextToken()));

            id++;
        }

        return stock;
    }

    public List<Stock> getStockList() {
        readFile();
        return sList;
    }

    public void addStock(Stock stock) {
        sList.add(stock);
        writeFile();
    }

    public List<Stock> findByKode(String search) {
        return sList.stream().filter(s -> s.getKode().equals(search)).toList();
    }
}
