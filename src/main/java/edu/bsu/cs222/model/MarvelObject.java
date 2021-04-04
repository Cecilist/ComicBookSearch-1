package edu.bsu.cs222.model;

public abstract class MarvelObject {
    private String name;
    private String id;
    private int comicsTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public int getComicsTotal() {
        return comicsTotal;
    }

}
