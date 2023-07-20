package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.project_studentmanagement_android.Model.GlobalUser;
import com.example.project_studentmanagement_android.Model.Teacher;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.User_Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin_UserAddNewUser extends AppCompatActivity {

    EditText edtiduser, edtusername, edtpass, edtrole;
    ImageView imgGoBack;
    Button btnAdd;
    List<String> lstRole;
    User_Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_add_new_user);
        AnhXa();

        Intent intent = getIntent();
        edtrole.setText(intent.getStringExtra("roleteacher"));
        edtrole.setEnabled(false);

        if(edtrole.getText().toString().equals("teacher"))
        {
            edtiduser.setHint("tch0");
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
                    if (role.equals("teacher")) {
                        Intent intent = new Intent(getApplicationContext(), User_AddNewTeacher.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(Admin_UserAddNewUser.this, "Infomation missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        edtiduser = (EditText) findViewById(R.id.edtAddUserIdUser);
        edtusername = (EditText) findViewById(R.id.edtUserName);
        edtpass = (EditText) findViewById(R.id.edtPassword);
        edtrole= (EditText) findViewById(R.id.edtAddUserRole);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnAdd = (Button) findViewById(R.id.btnAddUserAdd);

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