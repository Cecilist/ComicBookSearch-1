package edu.bsu.cs222.model;

public class Creator {
    private String name;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getCreators() {
        return getRole() + ": " + getName() + "\n";
    }
}
