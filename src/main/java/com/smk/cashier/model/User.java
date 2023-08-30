package com.smk.cashier.model;

public class User extends Model {
    private Integer id;
    private String userName;
    private String password;

    //Getters
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
