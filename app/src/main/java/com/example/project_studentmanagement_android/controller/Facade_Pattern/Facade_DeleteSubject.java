package com.example.project_studentmanagement_android.controller.Facade_Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Facade_DeleteSubject extends Database implements FacadeDeletedata {


    public Facade_DeleteSubject(Context context) {
        super(context);
    }

    @Override
    public boolean DeleteSubject(Context context, String SubId, int numclass) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] lstValue1 = {SubId};
        //lấy danh sách id Student học môn này có sinh viên trong lớp
        List<String> lsttemp = GetIdClassByIdSubject(SubId);
        String[] lstvalue2;


        //xóa môn

        if (database.delete("subject", "id_subject = ?", lstValue1) > 0) {
            //kiểm tra môn có lớp ko rồi kiểm tra có sinh viên ko
            if (lsttemp.size() > 0) {
                //xóa điểm
                for (int j = 0; j < lsttemp.size(); j++) {
                    lstvalue2 = new String[1];
                    lstvalue2[0] = lsttemp.get(j).toString();
                    database.delete("scores", "id_class = ?", lstvalue2);
                }
                //xóa lớp và teach
                if (database.delete("classes", "id_subject = ?", lstValue1) > 0 && database.delete("teach", "id_subject = ?", lstValue1) > 0)
                //nếu có sv trong lớp
                {
                    database.close();
                    return true;
                }
            } else {
                database.close();
                return true;
            }
        } else {
            database.close();
            return false;
        }
        return false;
    }

    //lấy id class đang dạy môn theo mã môn và có sv đang học
    public List<String> GetIdClassByIdSubject(String idSub) {
        String[] lstClass;
        List<String> lst = new ArrayList<String>();
        int i = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM classes WHERE id_subject = '" + idSub + "'", null);
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                lst.add(cursor.getString(2));
            }
        }
//            lstClass = new String[lst.size()];
//            for (i = 0; i < lst.size(); i++) {
//                lstClass[i] = lst.get(i);
//            }
        return lst;
    }


    @Override
    public boolean DeleteClass(Context context, String ClassId, String Idsub, int NumStudent) {
        return false;
    }
}