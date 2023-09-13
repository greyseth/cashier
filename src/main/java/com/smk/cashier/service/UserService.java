package com.smk.cashier.service;

import com.smk.cashier.model.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class UserService {
    FileWriter userWriter;
    FileReader userReader;
    private static UserService userService = null;

    List<User> userList = new LinkedList<>();

    private UserService() {
        try {
            userWriter = new FileWriter("user.txt");
            userReader = new FileReader("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }

        return userService;
    }

    private void readFile() {
        BufferedReader br = new BufferedReader(userReader);
        List<String> strList = br.lines().toList();

        userList = new LinkedList<>();
        for (String str:strList) {
            userList.add(parseToUser(str));
        }
    }

    private void writeFile() {
        BufferedWriter bw = new BufferedWriter(userWriter);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);

            //Appends string in StringBuilder
            StringBuilder sb = new StringBuilder();
            sb.append(user.getUsername());
            sb.append("|");
            sb.append(user.getPassword());

            //Writes appended string resuld
            try {
                bw.write(sb.toString());
                if (i < userList.size() - 1) {
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Closes the buffered writer
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User parseToUser(String str) {
        StringTokenizer st = new StringTokenizer(str, "|");

        int id = 0;
        User user = new User();
        while(st.hasMoreElements()) {
            if (id == 0) {
                user.setUsername(st.nextToken());
            }else if (id == 1) {
                user.setPassword(st.nextToken());
            }

            id++;
        }

        return user;
    }

    public List<User> getUserList() {
        //readFile sets the userList variable
        readFile();

        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
        writeFile();
    }
}
