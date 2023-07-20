package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.GlobalUser;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.Model.Teacher;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Profile_controller;
import com.example.project_studentmanagement_android.controller.User_Controller;

public class User_AddNewStudent extends AppCompatActivity {

    EditText edtidstudent, edtname, edtgender, edtbirthday, edtphone, edtaddress;
    ImageView imgGoBack;
    Button btnaddstd;
    User_Controller controllerUser;
    Profile_controller controllerStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__add_new_student);

        Anhxa();

        edtidstudent.setHint("student00");

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnaddstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    User user = new User(GlobalUser.getIduser(), GlobalUser.getUsename(), GlobalUser.getPass(), GlobalUser.getRole());

                    String iduser = GlobalUser.getIduser();
                    String idstudent = edtidstudent.getText().toString().trim();
                    String name = edtname.getText().toString().trim();
                    String gender = edtgender.getText().toString().trim();
                    String birthday = edtbirthday.getText().toString().trim();
                    String phone = edtphone.getText().toString().trim();
                    String address = edtaddress.getText().toString().trim();
                    Student student = new Student(iduser, idstudent, gender, address, birthday, name, phone);

                    if (controllerUser.AddUser(user)) {
                        if (controllerStudent.Addstudent(student)) {
                            Intent Home = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                            startActivity(Home);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Add teacher fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Add user fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void Anhxa() {
        edtidstudent = (EditText) findViewById(R.id.edtAddstdIdStudent);
        edtname = (EditText) findViewById(R.id.edtAddstdName);
        edtgender = (EditText) findViewById(R.id.edtAddstdGender);
        edtbirthday = (EditText) findViewById(R.id.edtAddstdBirthday);
        edtphone = (EditText) findViewById(R.id.edtAddstdPhone);
        edtaddress = (EditText) findViewById(R.id.edtAddstdAddress);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnaddstd = (Button) findViewById(R.id.btnAddNewStudent);
        controllerStudent = new Profile_controller(getApplicationContext());
        controllerUser = new User_Controller(getApplicationContext());
    }

    //Check input
    public boolean CheckInput() {
        String idstudent = edtidstudent.getText().toString().trim();
        String name = edtname.getText().toString().trim();
        String gender = edtgender.getText().toString().trim();
        String birthday = edtbirthday.getText().toString().trim();
        String phone = edtphone.getText().toString().trim();
        String address = edtaddress.getText().toString().trim();
        if (idstudent.equals("") || name.equals("") || phone.equals("") || gender.equals("") || birthday.equals("") || address.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}