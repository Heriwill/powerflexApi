package org.itb.mif3an.powerflex.model;

public enum UserRole {
    ADMIN("admin"),

    PROFESSOR("professor");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getPapel() {
        return role;
    }
}
