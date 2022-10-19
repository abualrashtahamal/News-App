package com.example.laststep;

public class User {

    private String Name, Email, Phone,Password;

    public User(String name, String email, String phone,String password) {
        Name = name;
        Email = email;
        Phone = phone;
        Password=password;

    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getPassword() {
        return Password;
    }
}
