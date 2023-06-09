
package controller;

public class Lawyer {
    int id;
    String name;
    String lastname;
    String address;
    String email;
    String phone;

    public Lawyer(int id, String name, String lastname, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
