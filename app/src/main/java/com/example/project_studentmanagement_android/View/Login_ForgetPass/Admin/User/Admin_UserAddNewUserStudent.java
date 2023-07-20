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
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.User_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_UserAddNewUserStudent extends AppCompatActivity {


    EditText edtiduser, edtusername, edtpass, edtrole;
    ImageView imgGoBack;
    Button btnAdd;
    List<String> lstRole;
    User_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_add_new_user_student);

        AnhXa();

        Intent intent = getIntent();
        edtrole.setText(intent.getStringExtra("rolestudent"));
        edtrole.setEnabled(false);

        if(edtrole.getText().toString().equals("student"))
        {
            edtiduser.setHint("st0");
        }


        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_UserManagementHomepage.class);
                startActivity(intent);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    String iduser = edtiduser.getText().toString().trim();
                    String uname = edtusername.getText().toString().trim();
                    String pass = edtpass.getText().toString().trim();
                    String role = edtrole.getText().toString().trim();
                    GlobalUser.setIduser(iduser);
                    GlobalUser.setUsename(uname);
                    GlobalUser.setPass(pass);
                    GlobalUser.setRole(role);
                    if (role.equals("student")) {
                        Intent intent = new Intent(getApplicationContext(), User_AddNewStudent.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Infomation missing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void AnhXa() {
        edtiduser = (EditText) findViewById(R.id.edtAddUserStudentIdUser);
        edtusername = (EditText) findViewById(R.id.edtAddUserStudentUserName);
        edtpass = (EditText) findViewById(R.id.edtAddUserStudentPassword);
        edtrole= (EditText) findViewById(R.id.edtAddUserStudentRole);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnAdd = (Button) findViewById(R.id.btnAddUserStudentAdd);

        lstRole = new ArrayList<>();
        controller = new User_Controller(getApplicationContext());
    }

    //Check input
    public boolean CheckInput() {
        String iduser = edtiduser.getText().toString().trim();
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