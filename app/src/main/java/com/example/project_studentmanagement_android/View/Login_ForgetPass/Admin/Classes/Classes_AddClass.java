package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalIdUserLogin;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Teacher_Controller;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Classes_AddClass extends AppCompatActivity {

    ImageView imgGoBack;
    EditText edtIdclass, edtIdsub, edtNameclass, edtTimeClass, edtMaxStudent, edtidteacher;
//    Spinner spinIdteacher;
    Button btnAddclass;

    Classes_Controler ClassController;
    Subject_Controller SubController;
    Teacher_Controller TeacherController;
    List<String> lstIdSub;
    List<String> lstIdTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes__add_class);
        Anhxa();
        edtidteacher.setText(TeacherController.GetIdTeacherByIdUser(GlobalIdUserLogin.getIduser()));

        edtIdsub.setText(GlobalSubject.getIdSubject());

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_ClassPage.class);
                startActivity(intent);
                finish();
            }
        });

        btnAddclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idSubject = edtIdsub.getText().toString().trim();
                String idTeacher = edtidteacher.getText().toString().trim();
                String idClass = edtIdclass.getText().toString().trim();
                String className = edtNameclass.getText().toString().trim();
                int numberStudent = 0;
                int maxstudent = Integer.parseInt(edtMaxStudent.getText().toString().trim());
                String classTime = edtTimeClass.getText().toString().trim();

                Classes classes = new Classes(idSubject, idTeacher, idClass, className, numberStudent, maxstudent, classTime);

                if (CheckInput()) {
                    if (ClassController.AddClass(classes, getApplicationContext(),idSubject)) {
                        Intent intent = new Intent(getApplicationContext(), Admin_ClassPage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }


    public void Anhxa() {
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        edtIdclass = (EditText) findViewById(R.id.edtIdClass);
        edtIdsub = (EditText) findViewById(R.id.edtIdSubject);
        edtidteacher =(EditText) findViewById(R.id.edtAddClassIdTeacher);
        edtNameclass = (EditText) findViewById(R.id.edtClassName);
        edtTimeClass = (EditText) findViewById(R.id.edtClassTime);
        edtMaxStudent = (EditText) findViewById(R.id.edtMaxStudentClass);
        btnAddclass = (Button) findViewById(R.id.btnAddClass);

        lstIdSub = new ArrayList<String>();
        lstIdTeacher = new ArrayList<String>();

        ClassController = new Classes_Controler(getApplicationContext());
        SubController = new Subject_Controller(getApplicationContext());
        TeacherController = new Teacher_Controller(getApplicationContext());

    }


    //Check input
    public boolean CheckInput() {
        String idSubject = edtIdsub.getText().toString().trim();
        String idTeacher = edtidteacher.getText().toString().trim();
        String idClass = edtIdclass.getText().toString().trim();
        String className = edtNameclass.getText().toString().trim();
        String maxstudent = edtMaxStudent.getText().toString().trim();
        String classTime = edtTimeClass.getText().toString().trim();

        if (idSubject.equals("") || idTeacher.equals("") || idClass.equals("") || maxstudent.equals("") || classTime.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (maxstudent.equals("0")) {
            Toast.makeText(this, "Max class can't 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!ClassController.CheckclassId(idClass, GlobalSubject.getIdSubject())) {
            Toast.makeText(this, "Id class existed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}