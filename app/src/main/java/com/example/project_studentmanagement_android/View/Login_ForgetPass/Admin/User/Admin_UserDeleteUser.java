package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Profile_controller;
import com.example.project_studentmanagement_android.controller.Teacher_Controller;
import com.example.project_studentmanagement_android.controller.User_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_UserDeleteUser extends AppCompatActivity {


    EditText edtusername, edtpass, edtrole;
    Spinner spiniduser;
    ImageView imgGoBack;
    Button btndelete;
    List<String> lstIduser;
    User_Controller controller;
    Teacher_Controller controllerTeacher;
    Profile_controller controllerStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_delete_usser);
        AnhXa();

        createSpinner();

        //set sự kiện click item spinner
        spiniduser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                User user = new User();
                user = controller.GetUsersByIdUser(lstIduser.get(position).toString());

                //gán thông tin ra màng hình
                edtusername.setText(user.getUsername().toString());
                edtpass.setText(user.getPassword().toString());
                edtrole.setText(user.getRole().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtrole.getText().toString().equals("student")) {
                    String idstudent = controllerStudent.GetIdstudentByIdUser(spiniduser.getSelectedItem().toString());
                    if (controllerStudent.DeleteStudent(getApplicationContext(), idstudent)) {
                        if (controller.DeleteUser(spiniduser.getSelectedItem().toString().trim())) {
                            Intent intent = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Admin_UserDeleteUser.this, "Delete user fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Admin_UserDeleteUser.this, "Delete student fail", Toast.LENGTH_SHORT).show();
                    }
                } else if (edtrole.getText().toString().equals("teacher")) {
                    String idteacher = controllerTeacher.GetIdTeacherByIdUser(spiniduser.getSelectedItem().toString());
                    if (controllerTeacher.DeleteTecher(idteacher)) {
                        if (controller.DeleteUser(spiniduser.getSelectedItem().toString().trim())) {
                            Intent intent = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Admin_UserDeleteUser.this, "Delete user fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Admin_UserDeleteUser.this, "Delete techer fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Admin_UserDeleteUser.this, "Missing Infomation", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void AnhXa() {
        spiniduser = (Spinner) findViewById(R.id.spinDeleteUserIdUser);
        edtusername = (EditText) findViewById(R.id.edtDeleteUserUserName);
        edtpass = (EditText) findViewById(R.id.edtDeleteUserPassword);
        edtrole = (EditText) findViewById(R.id.edtDeleteUserRole);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btndelete = (Button) findViewById(R.id.btnDeleteUserDelete);

        lstIduser = new ArrayList<>();
        controller = new User_Controller(getApplicationContext());
        controllerTeacher = new Teacher_Controller(getApplicationContext());
        controllerStudent = new Profile_controller(getApplicationContext());
    }

    //tạo spinner id class
    public void createSpinner() {
        lstIduser = controller.GetListIdUser();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstIduser);
        spiniduser.setAdapter(arrayAdapter);
    }

    //Check input
    public boolean CheckInput() {
        String iduser = spiniduser.getSelectedItem().toString().trim();
        String uname = edtusername.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();
        String role = edtrole.getText().toString().trim();

        if (iduser.equals("") || uname.equals("") || pass.equals("") || role.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}