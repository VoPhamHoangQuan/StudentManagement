package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study;

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
import com.example.project_studentmanagement_android.Model.GlobalClass;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Profile_controller;
import com.example.project_studentmanagement_android.controller.Scores_Controller;
import com.example.project_studentmanagement_android.controller.Subject_Controller;
import com.example.project_studentmanagement_android.controller.Teacher_Controller;

import java.util.ArrayList;
import java.util.List;

public class Study_DeletestudentOutClass extends AppCompatActivity {


    Spinner  spinidstd;
    EditText edtclassname, edtstdname, edtidclass;
    ImageView imgGoBack;
    Button btnLeft;

    Classes_Controler controllerClass;
    List<String> lstIdClass;
    Scores_Controller controllerScore;
    List<String> lstIdStudent;
    Profile_controller controllerProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study__deletestudent_out_class);

        Anhxa();

        edtidclass.setText(GlobalClass.getIdClass());
        edtclassname.setText(GlobalClass.getNameClass());

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                startActivity(intent);
                finish();
            }
        });

        //tạo spinner id class
        createIdStudentSpinner();

        //set sự kiện click item spinner
        spinidstd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                Student student = new Student();
                student = controllerProfile.GetStudentByIdStudent(lstIdStudent.get(position).toString());
                //gán thông tin ra màng hình;
                edtstdname.setText(student.getStudent_name().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idclass = edtidclass.getText().toString().trim();
                String idstudent = spinidstd.getSelectedItem().toString().trim();

                if (CheckInput()) {
                    if (controllerScore.DeleteScoresByIdStudent(getApplicationContext(), idstudent, idclass)) {
                        Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void Anhxa() {
        spinidstd = (Spinner) findViewById(R.id.spinLeftIdStudent);
        edtclassname = (EditText) findViewById(R.id.edtLeftClassName);
        edtstdname = (EditText) findViewById(R.id.edtLeftStudentName);
        edtidclass = (EditText) findViewById(R.id.edtLeftIdClass);
        btnLeft = (Button) findViewById(R.id.btnLeftClass);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);

        lstIdClass = new ArrayList<>();
        lstIdStudent = new ArrayList<>();
        controllerClass = new Classes_Controler(getApplicationContext());
        controllerScore = new Scores_Controller(getApplicationContext());
        controllerProfile = new Profile_controller(getApplicationContext());
    }

    //tạo spinner id student
    public void createIdStudentSpinner() {

        List<String> lstNull = new ArrayList<>();
        lstNull.add("không có sinh viên mới");
        lstIdStudent = controllerScore.GetListIdStudentInScore(GlobalClass.getIdClass());
        if (lstIdStudent != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstIdStudent);
            spinidstd.setAdapter(arrayAdapter);
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstNull);
            spinidstd.setAdapter(arrayAdapter);
        }
    }

    //Check input
    public boolean CheckInput() {
        String nameclass = edtclassname.getText().toString().trim();
        String namestd = edtstdname.getText().toString().trim();

        if (nameclass.equals("") || namestd.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}