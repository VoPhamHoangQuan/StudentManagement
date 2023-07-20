package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Student_Subject extends AppCompatActivity {

    ListView lstSubject;
    List<Subject> subjectsList;
    Subject_Controller controler;
    ImageView imgGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__subject);
        Anhxa();


        Subject_Controller subject_controller = new Subject_Controller(this);
        subjectsList = subject_controller.GetListSubjects();

        StudentSubjectAdapter adapter = new StudentSubjectAdapter(this, R.layout.activity_student__subject_view, subjectsList);
        lstSubject.setAdapter(adapter);

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Anhxa() {
        controler = new Subject_Controller(this);
        lstSubject = (ListView) findViewById(R.id.LstStudentSubject);
        subjectsList = new ArrayList<Subject>();
        imgGoBack = (ImageView) findViewById(R.id.ImgGoBack);
    }
}