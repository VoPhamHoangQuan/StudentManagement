package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.MD5;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.Model.User;

import java.util.ArrayList;
import java.util.List;

public class User_Controller extends Database {
    public User_Controller(Context context) {
        super(context);
    }

    //get data
    public Cursor GetUser() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from user", null);
        return res;
    }

    //gán data vào list<user>
    public List<User> GetListUsers() {
        String IdUser;
        String UserName;
        String Pass;
        String Role;

        List<User> Lstuser = new ArrayList<>();

        Cursor data = GetUser();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdUser = data.getString(0);
            UserName = data.getString(1);
            Pass = data.getString(2);
            Role = data.getString(3);

            Lstuser.add(new User(IdUser, UserName, Pass, Role));
        }
        data.close();
        return Lstuser;
    }

    //lấy list id giáo viên
    public List<String> GetListIdUser() {
        String IdUser;
        List<String> LstIdUser = new ArrayList<String>();

        Cursor data = GetUser();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdUser = data.getString(0);
            LstIdUser.add(IdUser);
        }
        data.close();
        return LstIdUser;
    }

    //lay user theo iduser
    public User GetUsersByIdUser(String iduser) {
        String IdUser;
        String UserName;
        String Pass;
        String Role;

        User user = new User();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from user WHERE Id ='" + iduser + "'", null);

        if (res != null) {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                user.setId(res.getString(0));
                user.setUsername(res.getString(1));
                user.setPassword(res.getString(2));
                user.setRole(res.getString(3));
            }
        }
        res.close();
        return user;
    }


    //kiểm tra id user mới có trùng không
    public boolean CheckTeacherId(String userid) {
        List<String> lstIduser = GetListIdUser();
        if (lstIduser.size() > 0) {
            for (int i = 0; i < lstIduser.size(); i++) {
                if (userid.equals(lstIduser.get(i).toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    //thêm user
    public boolean AddUser(User user) {

        String iduser = user.getId();
        String username = user.getUsername();
        String psss = user.getPassword();
        String role = user.getRole();

        String Md5pass = MD5.encode(psss);

        //thêm vào bảng teacher
        ContentValues valuesUser = new ContentValues();
        valuesUser.put("Id", iduser);
        valuesUser.put("username", username);
        valuesUser.put("password", Md5pass);
        valuesUser.put("role", role);
        //kiểm tra id và thêm dữ liệu
        SQLiteDatabase database = this.getWritableDatabase();
        if (CheckTeacherId(iduser) && database.insert("user", null, valuesUser) > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }


    //xóa user
    public boolean DeleteUser(String iduser) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] lstiduser = {iduser};
        if (database.delete("user", "Id = ?", lstiduser) > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    //lay id user bang username, pass
    public String GetIdUsersByLoginInfo(String uname, String psss) {
        String IdUser;
        String md5pass = MD5.encode(psss);

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select Id from user WHERE username ='" + uname + "' AND password = '"+md5pass+"'", null);

        if (res != null) {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                IdUser = res.getString(0);
                res.close();
                return IdUser;
            }
        }
        res.close();
        return "null";
    }
}
