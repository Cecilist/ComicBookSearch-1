package edu.bsu.cs222;

public class Creator {
    private final String name;
    private final String role;

    public Creator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getCreators(){
        return getRole()+": "+getName()+"\n";
    }
}
