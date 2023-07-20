package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.GlobalSchedule;
import com.example.project_studentmanagement_android.R;

public class Schedule extends AppCompatActivity {

    TextView Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    ImageView imgGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Anhxa();

        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Monday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Tuesday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Wednesday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Thursday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Friday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Saturday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Sunday");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
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
        Monday = (TextView) findViewById(R.id.Monday);
        Tuesday = (TextView) findViewById(R.id.Tuesday);
        Wednesday = (TextView) findViewById(R.id.Wednesday);
        Thursday = (TextView) findViewById(R.id.Thursday);
        Friday = (TextView) findViewById(R.id.Friday);
        Saturday = (TextView) findViewById(R.id.Saturday);
        Sunday = (TextView) findViewById(R.id.Sunday);
        imgGoBack = (ImageView) findViewById(R.id.ImgGoBack);
    }
}