package edu.bsu.cs222.model;

public class Creators {
    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreators() {
        return getRole() + ": " + getName() + "\n";
    }
}
