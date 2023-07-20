package com.example.project_studentmanagement_android.Model;

public class GlobalUser {
    static public String iduser;
    static public String usename;
    static public String pass;
    static public String role;

    public GlobalUser() {
    }

    public static String getIduser() {
        return iduser;
    }

    public static void setIduser(String iduser) {
        GlobalUser.iduser = iduser;
    }

    public static String getUsename() {
        return usename;
    }

    public static void setUsename(String usename) {
        GlobalUser.usename = usename;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        GlobalUser.pass = pass;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        GlobalUser.role = role;
    }
}
