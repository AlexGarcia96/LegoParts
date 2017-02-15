package com.example.alex.legoparts;

/**
 * Created by alex on 26/1/17.
 */

public class components {

    protected String id;
    protected String color;
    protected String type;
    protected String colorNombre;
    protected String imgUrl;

    public components(String id, String color, String type, String colorNombre, String imgUrl) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.colorNombre = colorNombre;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColorNombre() {
        return colorNombre;
    }

    public void setColorNombre(String colorNombre) {
        this.colorNombre = colorNombre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
