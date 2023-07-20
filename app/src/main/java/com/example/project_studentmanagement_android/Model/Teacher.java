package com.example.project_studentmanagement_android.Model;

public class Teacher {
    String id_user;
    String id_teacher;
    String teacher_mail;
    String teacher_Name;
    String teacher_phone;

    public Teacher() {
    }

    public Teacher(String id_user, String id_teacher, String teacher_mail, String teacher_Name, String teacher_phone) {
        this.id_user = id_user;
        this.id_teacher = id_teacher;
        this.teacher_mail = teacher_mail;
        this.teacher_Name = teacher_Name;
        this.teacher_phone = teacher_phone;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(String id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getTeacher_mail() {
        return teacher_mail;
    }

    public void setTeacher_mail(String teacher_mail) {
        this.teacher_mail = teacher_mail;
    }

    public String getTeacher_Name() {
        return teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        this.teacher_Name = teacher_Name;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public void setTeacher_phone(String teacher_phone) {
        this.teacher_phone = teacher_phone;
    }
}
