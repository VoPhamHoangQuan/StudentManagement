package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.FacadeDeletedata;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.Facade_DeleteSubject;

import java.util.ArrayList;
import java.util.List;

public class Subject_Controller extends Database {

    public Subject_Controller(Context context) {
        super(context);
    }


    //get data
    public Cursor GetSubject() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from subject", null);
        return res;
    }

    //lay danh sach mon hoc duoc day theo id user login vao
    public Cursor GetSubject2(String iduser) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from subject INNER JOIN teach ON subject.id_subject = teach.id_subject INNER JOIN teacher ON teach.id_teacher = teacher.id_teacher WHERE teacher.id_user = '" + iduser + "'", null);
        return res;
    }

    //gán data GetSubject vào list<Subject>
    public List<Subject> GetListSubjects() {
        String IdSubject;
        String SubjectName;
        int Credits;
        int NumberClass;
        int MaxClass;

        List<Subject> LstSubject = new ArrayList<Subject>();

        Cursor data = GetSubject();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);
            SubjectName = data.getString(1);
            Credits = data.getInt(2);
            NumberClass = data.getInt(3);
            MaxClass = data.getInt(4);


            LstSubject.add(new Subject(IdSubject, SubjectName, Credits, NumberClass, MaxClass));
        }
        data.close();
        return LstSubject;
    }

    //gán data GetSubject2 vào list<Subject>
    public List<Subject> GetListSubjectsByIdUser(String iduser) {
        String IdSubject;
        String SubjectName;
        int Credits;
        int NumberClass;
        int MaxClass;

        List<Subject> LstSubject = new ArrayList<Subject>();

        Cursor data = GetSubject2(iduser);
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);
            SubjectName = data.getString(1);
            Credits = data.getInt(2);
            NumberClass = data.getInt(3);
            MaxClass = data.getInt(4);

            LstSubject.add(new Subject(IdSubject, SubjectName, Credits, NumberClass, MaxClass));
        }
        data.close();
        return LstSubject;
    }


    //Thêm môn
    public boolean AddSubject(Subject subject, String id_teacher) {
        String idSubject = subject.getIdSubject();
        String subjectName = subject.getSubjectName();
        int credits = subject.getCredits();
        int numberClass = subject.getNumberClass();
        int maxClass = subject.getMaxClass();

        ContentValues values = new ContentValues();
        values.put("id_subject", idSubject);
        values.put("subject_name", subjectName);
        values.put("credits", credits);
        values.put("number_class", 1);
        values.put("max_class", maxClass);

        ContentValues valuesclass = new ContentValues();
        valuesclass.put("id_class", idSubject + "Class01");
        valuesclass.put("id_subject", idSubject);
        valuesclass.put("id_teacher", id_teacher);
        valuesclass.put("class_name", "Class 01");
        valuesclass.put("number_student", 0);
        valuesclass.put("max_student", 3);
        valuesclass.put("time", "Monday, T1-3");

        ContentValues valuesteach = new ContentValues();
        valuesteach.put("id_subject", idSubject);
        valuesteach.put("id_teacher", id_teacher);
        valuesteach.put("id_class", idSubject + "Class01");
        SQLiteDatabase database = this.getWritableDatabase();
        if (database.insert("subject", null, values) > 0) {
            if (database.insert("classes", null, valuesclass) > 0) {
                if (database.insert("teach", null, valuesteach) > 0) {
                    database.close();
                    return true;
                } else {
                    database.close();
                    return false;
                }
            } else {
                database.close();
                return false;
            }
        } else {
            database.close();
            return false;
        }
    }


    //kiểm tra id subject có trùng ko
    public boolean CheckSubjectId(String subjectid) {
        List<Subject> lstsub = GetListSubjects();
        if (lstsub.size() > 0) {
            for (int i = 0; i < lstsub.size(); i++) {
                if (subjectid.equals(lstsub.get(i).getIdSubject().toString().trim())) {
                    return false;
                }
            }
        } else {
            //không có lớp nào trong data
            return false;
        }
        return true;
    }


    //lấy Id môn cho vào mảng
    public List<String> GetListIdSubjects() {
        String IdSubject;

        List<String> LstIdSubject = new ArrayList<String>();

        Cursor data = GetSubject();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);

            LstIdSubject.add(IdSubject);
        }
        data.close();
        return LstIdSubject;
    }

   //lấy danh sách mã lớp trong bảng teach theo mã môn
    public List<String> GetIdClassesInTeachByIdSub(String idsub)
    {
        List<String> lstidclass = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teach WHERE id_subject = '" + idsub + "'", null);
        for(res.moveToFirst();!res.isAfterLast();res.moveToNext())
        {
            lstidclass.add(res.getString(2).toString());
        }
        return lstidclass;
    }


    //lấy thông tin môn theo id
    public Subject GetSubjectsById(String id) {

        Subject subject = new Subject();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM subject WHERE id_subject = '" + id + "'", null);

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            subject.setIdSubject(res.getString(0));
            subject.setSubjectName(res.getString(1));
            subject.setCredits(res.getInt(2));
            subject.setNumberClass(res.getInt(3));
            subject.setMaxClass(res.getInt(4));
        }
        res.close();
        return subject;
    }


    //xóa môn
    public void DeleteSubject(Context context, String id, int numclass) {
        try {
            FacadeDeletedata deletedata = new Facade_DeleteSubject(context);

            if (deletedata.DeleteSubject(context, id, numclass)) {
                Toast.makeText(context, "Delete Subject Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Delete Subject Fail", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            Toast.makeText(context, "Error Delete Unsuccess", Toast.LENGTH_LONG).show();
        }
    }

    //lấy id class đang dạy môn theo mã môn
    public String[] GetIdClassByIdSubject(String idSub) {
        String[] lstClass;
        List<String> lst = new ArrayList<String>();
        int i = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM classes WHERE id_subject = '" + idSub + "'", null);
//        Cursor cursor = database.query("classes",null,"id_subject"+"= ?",new String[]{idSub},null,null,null);
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                lst.add(cursor.getString(2));
            }
        }
        lstClass = new String[lst.size()];
        for (i = 0; i < lst.size(); i++) {
            lstClass[i] = lst.get(i);
        }
        cursor.close();

        return lstClass;
    }


    //sửa thông tin môn học
    public boolean EditSubject(Subject subject) {
        String idSubject = subject.getIdSubject().trim();
        String subjectName = subject.getSubjectName().trim();
        int credit = subject.getCredits();
        int maxClass = subject.getMaxClass();

        ContentValues cv = new ContentValues();
        cv.put("subject_name", subjectName);
        cv.put("credits", credit);
        cv.put("max_class", maxClass);

        SQLiteDatabase database = this.getWritableDatabase();
        database.update("subject", cv, "id_subject = ?", new String[]{idSubject});
        return true;
    }

    //lấy danh sách subject bằng danh sách mã subject
    public List<Subject> GetListsubjectByListIdSubject(List<Subject> lst) {
        List<Subject> lstsubject = new ArrayList<>();

        return lstsubject;
    }


    // Lấy danh sách các môn trong bảng scores theo mã giáo viên mà không lặp lại
    public List<Subject> GetListSubjectsByIdUserNotRepeat(String iduser) {
        List<Subject> lstSubject = GetListSubjectsByIdUser(iduser);
        int dem = 0;

        if (lstSubject.size() > 1) {
            for (int j = 0; j < lstSubject.size(); j++) {
                dem = 0;
                for (int z = 0; z < lstSubject.size(); z++) {
                    if (lstSubject.get(j).getIdSubject().toString().equals(lstSubject.get(z).getIdSubject().toString())) {
                        dem++;
                    }
                }
                if (dem > 1) {
                    lstSubject.remove(j);
                }
            }
            return lstSubject;

        }
        return lstSubject;
    }



}
