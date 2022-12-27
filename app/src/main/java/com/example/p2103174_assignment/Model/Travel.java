package com.example.p2103174_assignment.Model;

public class Travel {

    int id;
    int images;
    String name;
    String ID;
    String desc;
    String expDate;

    public Travel()
    {

    }

    public Travel(int id, int images, String name, String ID, String desc, String expDate) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.ID = ID;
        this.desc = desc;
        this.expDate = expDate;
    }

    public Travel(String s) {

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String price) {
        this.ID = price;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
