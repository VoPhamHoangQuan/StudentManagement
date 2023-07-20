package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.Teacher;
import com.example.project_studentmanagement_android.controller.Database;

import java.util.ArrayList;
import java.util.List;

public class Teacher_Controller extends Database {
    public Teacher_Controller(Context context) {
        super(context);
    }

    //get data
    public Cursor GetTeacher() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teacher", null);
        return res;
    }

    // lấy thông tin 1 giáo viên bang id teacher
    public Teacher GetT1eacher(String idteacher) {
        Teacher teacher = new Teacher();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teacher WHERE id_teacher = '" + idteacher + "'", null);
        if (res != null) {
            teacher.setId_user(res.getString(0));
            teacher.setId_teacher(res.getString(1));
            teacher.setTeacher_mail(res.getString(2));
            teacher.setTeacher_Name(res.getString(3));
            teacher.setTeacher_phone(res.getString(4));
        }
        return teacher;
    }

    // lấy thông tin 1 giáo viên bang iduser
    public Teacher Get1TeacherByIdUser(String iduser) {
        Teacher teacher = new Teacher();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teacher WHERE id_user = '" + iduser + "'", null);
        if (res != null) {
            res.moveToFirst();
            teacher.setId_user(res.getString(0));
            teacher.setId_teacher(res.getString(1));
            teacher.setTeacher_mail(res.getString(2));
            teacher.setTeacher_Name(res.getString(3));
            teacher.setTeacher_phone(res.getString(4));
        }
        res.close();
        return teacher;
    }

    //lấy id giáo viên cho vào list
    public List<String> GetListIdTeacher() {
        String IdTeacher;
        List<String> LstIdTeacher = new ArrayList<String>();

        Cursor data = GetTeacher();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdTeacher = data.getString(1);

            LstIdTeacher.add(IdTeacher);
        }
        data.close();
        return LstIdTeacher;
    }


    //kiểm tra id giáo viên mới có trùng không
    public boolean CheckTeacherId(String teacherid) {
        List<String> lstIdTeacher = GetListIdTeacher();
        if (lstIdTeacher.size() > 0) {
            for (int i = 0; i < lstIdTeacher.size(); i++) {
                if (teacherid.equals(lstIdTeacher.get(i).toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    //lấy id user bằng id teacher
    public String GetIduserByIdTeacher(String idteacher) {
        Teacher teacher = GetT1eacher(idteacher);
        String iduser = teacher.getId_user();
        return iduser;
    }

    //lấy id teacher bằng id user
    public String GetIdTeacherByIdUser(String iduser) {
        Teacher teacher = Get1TeacherByIdUser(iduser);
        String idteacher = teacher.getId_teacher();
        return idteacher;
    }


    //thêm giáo viên mới
    public boolean AddTeacher(Teacher teacher) {
        String iduser = teacher.getId_user();
        String idteacher = teacher.getId_teacher();
        String mail = teacher.getTeacher_mail();
        String phone = teacher.getTeacher_phone();
        String name = teacher.getTeacher_Name();

        //thêm vào bảng teacher
        ContentValues valuesTeacher = new ContentValues();
        valuesTeacher.put("id_user", iduser);
        valuesTeacher.put("id_teacher", idteacher);
        valuesTeacher.put("teacher_mail", mail);
        valuesTeacher.put("teacher_name", name);
        valuesTeacher.put("teacher_phone", phone);
        //kiểm tra id và thêm dữ liệu
        SQLiteDatabase database = this.getWritableDatabase();
        if (CheckTeacherId(idteacher) && database.insert("teacher", null, valuesTeacher) > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    //Cập nhật thông tin giáo viên
    public boolean EditTeacher(Teacher teacher) {
        String idteacher = teacher.getId_teacher();
        String mail = teacher.getTeacher_mail();
        String phone = teacher.getTeacher_phone();
        String name = teacher.getTeacher_Name();

        ContentValues valuesTeacher = new ContentValues();
        valuesTeacher.put("teacher_mail", mail);
        valuesTeacher.put("teacher_name", name);
        valuesTeacher.put("teacher_phone", phone);

        SQLiteDatabase database = this.getWritableDatabase();
        database.update("teacher", valuesTeacher, "id_teacher = ?", new String[]{idteacher});
        database.close();
        return true;
    }


    //xóa giáo viên
    public boolean DeleteTecher(String teacherid) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] lstidteacher = {teacherid};
        List<String> lstidclasstemp = GetListIdClassByIdTeacher(teacherid);
        List<String> lstidsubtemp = GetListIdSubjectByIdTeacher(teacherid);
        if (lstidclasstemp != null) {
            database.delete("teach", "id_teacher = ?", lstidteacher);
            database.delete("classes", "id_teacher = ?", lstidteacher);
            for(int i=0;i<lstidclasstemp.size();i++) {
                String[] lstidClass = new String[1];
                lstidClass[0] = lstidclasstemp.get(i).toString().trim();
                database.delete("scores", "id_class = ?", lstidClass);
            }
            database.delete("teacher", "id_teacher = ?", lstidteacher);
            for(int j=0;j<lstidsubtemp.size();j++) {
                String[] lstidsub = new String[1];
                lstidsub[0] = lstidsubtemp.get(j).toString().trim();
                database.delete("subject", "id_subject = ?", lstidsub);
            }
            return true;
        }
        return false;
    }

    //lấy danh sách id lớp giáo viên dạy
    public List<String> GetListIdClassByIdTeacher(String idteacher) {
        List<String> lsttemp = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teach WHERE id_teacher ='" + idteacher + "'", null);
        if (res != null) {
            res.moveToFirst();
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                lsttemp.add(res.getString(2));
            }
        }
        return lsttemp;
    }

    //lấy danh sách mã môn giáo viên đang dạy
    public List<String> GetListIdSubjectByIdTeacher(String idteacher) {
        List<String> lsttemp = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teach WHERE id_teacher ='" + idteacher + "'", null);
        if (res != null) {
            res.moveToFirst();
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                lsttemp.add(res.getString(  0));
            }
        }
        return lsttemp;
    }

}



