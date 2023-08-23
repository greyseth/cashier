package com.smk.cashier;
import com.smk.cashier.model.*;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        User user = new User();
        user.setId(0);
        user.setUsername("Grey");
        user.setPassword("1234");

        Scanner scanner = new Scanner(System.in);

        System.out.println("---Cashier app 3000---\nLog in to access the application:");
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        if (username == user.getUsername() && password == user.getPassword()) {
            System.out.println("Welcome, "+user.getUsername()+".");
        }else {
            System.out.println("Invalid credentials");
        }
    }
}
