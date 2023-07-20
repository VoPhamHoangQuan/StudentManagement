package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalClass;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_AddClass;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_DeleteClass;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_EditClass;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study.Admin_StudyPage;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.User_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_UserManagementHomepage extends AppCompatActivity {

    ListView lvuser;
    ImageView imgGoBack;
    UserManageAdapter adapter;
    List<User> lstUser;
    User_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_management_homepage);
        Anhxa();
        //load list view
        lstUser = controller.GetListUsers();
        adapter = new UserManageAdapter(this, R.layout.activity_user_view, lstUser);
        lvuser.setAdapter(adapter);


        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Anhxa() {
        lvuser = (ListView) findViewById(R.id.lVUser);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        lstUser = new ArrayList<>();
        controller = new User_Controller(getApplicationContext());
    }

    //tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_userpagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //sự kiện menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AddStudentAcount:
                Intent AddStudentAcount = new Intent(getApplicationContext(), Admin_UserAddNewUserStudent.class);
                AddStudentAcount.putExtra("rolestudent", "student");
                startActivity(AddStudentAcount);
                finish();
                break;
            case R.id.AddTeacherAcount:
                Intent AddTeacherAcount = new Intent(getApplicationContext(), Admin_UserAddNewUser.class);
                AddTeacherAcount.putExtra("roleteacher", "teacher");
                startActivity(AddTeacherAcount);
                finish();
                break;
            case R.id.DeleteAccount:
                Intent UserDeleteUser = new Intent(getApplicationContext(), Admin_UserDeleteUser.class);
                startActivity(UserDeleteUser);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}