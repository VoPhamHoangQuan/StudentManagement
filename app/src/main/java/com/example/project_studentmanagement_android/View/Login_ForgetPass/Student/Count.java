package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Scores_Controller;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;

import java.util.ArrayList;
import java.util.List;

public class Count extends AppCompatActivity {
//    String name;
    ImageView imgGoBack;
    ListView lstClasses;
    List<Classes> classesList;
    Classes_Controler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        Anhxa();

        Classes_Controler classes_controler = new Classes_Controler(this);
//        Scores_Controller scores_controller = new Scores_Controller(this);
//        GlobalStudentName managerUser = new GlobalStudentName();
//        name = managerUser.getFlagName();

        String id_student = SetIdStudent.getFlagID();
        classesList = classes_controler.GetListClasses2(id_student);

        StudentAdapter adapter = new StudentAdapter(this, R.layout.activity_student_view, classesList);
        lstClasses.setAdapter(adapter);
        lstClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Count_Detail.class);
                intent.putExtra("id_class",classesList.get(position).getIdClass());
                startActivity(intent);
            }
        });


        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        lstClasses = (ListView) findViewById(R.id.LstClasses);
        controler = new Classes_Controler(this);
        classesList = new ArrayList<Classes>();
        imgGoBack = (ImageView) findViewById(R.id.ImgGoBack);
    }
}