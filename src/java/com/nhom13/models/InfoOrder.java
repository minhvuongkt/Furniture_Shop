package com.nhom13.models;

public class InfoOrder {

    String Name, Email, Phone, Address, Province;

    public InfoOrder(String name, String email, String phone, String address, String province) {
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Address = address;
        this.Province = province;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getProvince() {
        return Province;
    }
}
