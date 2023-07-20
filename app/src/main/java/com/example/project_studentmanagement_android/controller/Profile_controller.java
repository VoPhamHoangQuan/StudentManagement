package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.GlobalUser;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.Model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Profile_controller extends Database {

    public Profile_controller(Context context) {
        super(context);
    }


    public boolean updateProfileStudent(String id, String gender, String address, String birthday, String name, String phone) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_gender", gender);
        values.put("student_address", address);
        values.put("student_birthday", birthday);
        values.put("student_name", name);
        values.put("student_phone", phone);
        int row = database.update("student", values, "id_user = ?", new String[]{id});
        if (row <= 0)
            return false;
        else
            return true;
    }


    //lấy thông tin sinh viên theo mã sinh viên
    public Student GetStudentByIdStudent(String idStd) {
        Student student = new Student();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from student WHERE id_student ='" + idStd + "'", null);
        if (res != null) {
            res.moveToFirst();
            student.setId_user(res.getString(0));
            student.setId_student(res.getString(1));
            student.setStudent_gender(res.getString(2));
            student.setStudent_address(res.getString(3));
            student.setStudent_birthday(res.getString(4));
            student.setStudent_name(res.getString(5));
            student.setStudent_phone(res.getString(6));
            res.moveToFirst();
            res.close();
            return student;
        }
        res.moveToFirst();
        res.close();
        return student;
    }


    //Thêm student
    public boolean Addstudent(Student student) {
        String iduser = student.getId_user();
        String idstudent = student.getId_student();
        String name = student.getStudent_name();
        String gender = student.getStudent_gender();
        String birthday = student.getStudent_birthday();
        String phone = student.getStudent_phone();
        String address = student.getStudent_address();

        //thêm vào bảng teacher
        ContentValues valuesStudent = new ContentValues();
        valuesStudent.put("id_user", iduser);
        valuesStudent.put("id_student", idstudent);
        valuesStudent.put("student_gender", gender);
        valuesStudent.put("student_address", address);
        valuesStudent.put("student_birthday", birthday);
        valuesStudent.put("student_name", name);
        valuesStudent.put("student_phone", phone);

        //kiểm tra id và thêm dữ liệu
        SQLiteDatabase database = this.getWritableDatabase();
        if (CheckStudentId(idstudent) && database.insert("student", null, valuesStudent) > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    //kiểm tra id sinh viên mới có trùng không
    public boolean CheckStudentId(String studentid) {
        List<String> lstidstudent = GetListIdStudent();
        if (lstidstudent.size() > 0) {
            for (int i = 0; i < lstidstudent.size(); i++) {
                if (studentid.equals(lstidstudent.get(i).toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    //lấy id giáo viên cho vào list
    public List<String> GetListIdStudent() {
        String Idstudent;
        List<String> LstIdstudent = new ArrayList<String>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor data = database.rawQuery("select * from student", null);
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            Idstudent = data.getString(1);
            LstIdstudent.add(Idstudent);
        }
        data.close();
        return LstIdstudent;
    }

    // lấy id sinh viên theo iduser
    public String GetIdstudentByIdUser(String iduser) {
        String idteacher;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from student WHERE id_user = '" + iduser + "'", null);
        res.moveToFirst();
        idteacher = res.getString(1);
        res.close();
        return idteacher;
    }

    // lấy id class sinh viên học
    public List<String> GetIdclassByIdstd(String idstudent) {
        List<String> lstidclass = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from scores WHERE id_student = '" + idstudent + "'", null);
        if (res != null) {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext())
                lstidclass.add(res.getString(0).toString());
        }
        return lstidclass;
    }

    //xóa sinh viên
    public boolean DeleteStudent(Context context, String idstudent) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] lstidstudent = {idstudent};
        List<String> lstidclass = GetIdclassByIdstd(idstudent);
        Scores_Controller controlerScore = new Scores_Controller(context);
        if (lstidclass.size() > 0) {
            database.delete("scores", "id_student = ?", lstidstudent);
            for (int i = 0; i < lstidclass.size(); i++) {
                String idclass = lstidclass.get(i).toString();
                controlerScore.UpdateNumberStdInClassAfterDelete(context, idclass);
            }
            database.delete("student", "id_student = ?", lstidstudent);
            return true;
        } else {
            if (database.delete("student", "id_student = ?", lstidstudent) > 0)
                return true;
            else
                return false;
        }
    }
}
