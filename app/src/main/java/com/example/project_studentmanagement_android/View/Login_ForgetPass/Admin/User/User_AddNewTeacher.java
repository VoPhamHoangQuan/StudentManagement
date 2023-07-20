package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.GlobalUser;
import com.example.project_studentmanagement_android.Model.Teacher;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.Teacher_Controller;
import com.example.project_studentmanagement_android.controller.User_Controller;

import java.util.ArrayList;
import java.util.List;

public class User_AddNewTeacher extends AppCompatActivity {

    EditText edtidteacher, edtteachername, edtteacherphone, edtteachermail;
    ImageView imgGoBack;
    Button btnAddteacher;

    User_Controller controllerUser;
    Teacher_Controller controllerTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__add_new_teacher);
        AnhXa();

        edtidteacher.setHint("teacher00");

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnAddteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    User user = new User(GlobalUser.getIduser(),GlobalUser.getUsename(),GlobalUser.getPass(),GlobalUser.getRole());

                    String iduser = GlobalUser.getIduser();
                    String idteacher = edtidteacher.getText().toString().trim();
                    String mail = edtteachermail.getText().toString().trim();
                    String name = edtteachername.getText().toString().trim();
                    String phone = edtteacherphone.getText().toString().trim();
                    Teacher teacher = new Teacher(iduser, idteacher, mail, name, phone);

                    if (controllerUser.AddUser(user)) {
                        if (controllerTeacher.AddTeacher(teacher)) {
                            Intent Home = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                            startActivity(Home);
                            finish();
                        } else {
                            Toast.makeText(User_AddNewTeacher.this, "Add teacher fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(User_AddNewTeacher.this, "Add user fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void AnhXa() {
        edtidteacher = (EditText) findViewById(R.id.edtAddNewteacherIdTeacher);
        edtteachername = (EditText) findViewById(R.id.edtAddnewtecherNameTeacher);
        edtteacherphone = (EditText) findViewById(R.id.edtAddnewtecherPhone);
        edtteachermail = (EditText) findViewById(R.id.edtAddnewtecherEmail);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnAddteacher = (Button) findViewById(R.id.btnAddNewteacherAdd);

        controllerTeacher = new Teacher_Controller(getApplicationContext());
        controllerUser = new User_Controller(getApplicationContext());
    }

    //Check input
    public boolean CheckInput() {
        String idteacher = edtidteacher.getText().toString().trim();
        String name = edtteachername.getText().toString().trim();
        String phone = edtteacherphone.getText().toString().trim();
        String mail = edtteachermail.getText().toString().trim();

        if (idteacher.equals("") || name.equals("") || phone.equals("") || mail.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}