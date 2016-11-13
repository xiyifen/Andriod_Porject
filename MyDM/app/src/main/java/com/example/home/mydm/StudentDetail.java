package com.example.home.mydm;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetail extends ActionBarActivity implements android.view.View.OnClickListener{
    Cursor cursor;
    Button btnSave ,  btnDelete;
    Button btnClose;
    //Button btnnext;
//    CheckBox btnbj;//病假
//    CheckBox btncd;//迟到
//    CheckBox btncj;//在勤
//    CheckBox btnqq;//缺勤
    CheckBox cbzq;
    CheckBox cbkk;
    CheckBox cbbj;
    CheckBox cbcd;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    EditText editTextbz;
    EditText editTextbj;
    EditText editTextcd;
    EditText editTextcj;
    private int _Student_Id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        cbzq = (CheckBox) findViewById(R.id.cbzq);
        cbbj = (CheckBox) findViewById(R.id.cbbj);
        cbcd = (CheckBox) findViewById(R.id.cbcd);
        cbkk = (CheckBox) findViewById(R.id.cbkk);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextbz = (EditText) findViewById(R.id.editTextbz);
        editTextbj = (EditText) findViewById(R.id.editTextbj);
        editTextcd = (EditText) findViewById(R.id.editTextcd);
        editTextcj = (EditText) findViewById(R.id.editTextcj);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        cbzq.setOnClickListener(this);
        cbbj.setOnClickListener(this);
        cbcd.setOnClickListener(this);
        //btnnext.setOnClickListener(this);
        cbkk.setOnClickListener(this);


        _Student_Id =0;
        Intent intent = getIntent();
        _Student_Id =intent.getIntExtra("student_Id", 0);
        StudentRepo repo = new StudentRepo(this);
        Student student = new Student();
        student = repo.getStudentById(_Student_Id);

        editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(student.name);
        editTextEmail.setText(student.email);
        editTextbz.setText(String.valueOf(student.bz));
        editTextbj.setText(String.valueOf(student.bj));
        editTextcd.setText(String.valueOf(student.cd));
        editTextcj.setText(String.valueOf(student.cj));
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            if(editTextName.getText().toString().length()!=0){
                student.name=editTextName.getText().toString();
            }else{
                Toast.makeText(getApplication(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                return;

            }
        if(editTextEmail.getText().toString().length()!=0){
            student.email=editTextEmail.getText().toString();
        }else {
            Toast.makeText(getApplication(), "学号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

            student.age= Integer.parseInt(editTextAge.getText().toString());
            student.bz=Integer.parseInt(editTextbz.getText().toString());
            student.bj=Integer.parseInt(editTextbj.getText().toString());
            student.cd=Integer.parseInt(editTextcd.getText().toString());
            student.cj=Integer.parseInt(editTextcj.getText().toString());
            student.student_ID=_Student_Id;

            if (_Student_Id==0){
                _Student_Id = repo.insert(student);

                Toast.makeText(this,"添加学生成功",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(student);
                Toast.makeText(this,"学生信息修改成功",Toast.LENGTH_SHORT).show();
            }

        }
        else if (view== findViewById(R.id.btnDelete)){
            StudentRepo repo = new StudentRepo(this);
            repo.delete(_Student_Id);
            Toast.makeText(this, "学生信息删除成功", Toast.LENGTH_SHORT);
            finish();
        }
        else if (view== findViewById(R.id.cbzq)){
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            student.age= Integer.parseInt(editTextAge.getText().toString())+1;
            student.email=editTextEmail.getText().toString();
            student.name=editTextName.getText().toString();
            student.bz=Integer.parseInt(editTextbz.getText().toString());
            student.bj=Integer.parseInt(editTextbj.getText().toString());
            student.cd=Integer.parseInt(editTextcd.getText().toString());
            student.cj=Integer.parseInt(editTextcj.getText().toString());
            student.student_ID=_Student_Id;

            /*if (_Student_Id==0){
                _Student_Id = repo.insert(student);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }
            else*///{

                repo.update(student);
                Toast.makeText(this,"学生信息修改成功",Toast.LENGTH_SHORT).show();
          //  }
            finish();
        }
        else if (view== findViewById(R.id.cbkk)){
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            student.age= Integer.parseInt(editTextAge.getText().toString());
            student.email=editTextEmail.getText().toString();
            student.name=editTextName.getText().toString();
            student.bz=Integer.parseInt(editTextbz.getText().toString())+1;
            student.bj=Integer.parseInt(editTextbj.getText().toString());
            student.cd=Integer.parseInt(editTextcd.getText().toString());
            student.cj=Integer.parseInt(editTextcj.getText().toString());
            student.student_ID=_Student_Id;

            if (_Student_Id==0){
                _Student_Id = repo.insert(student);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(student);
                Toast.makeText(this,"学生信息修改成功",Toast.LENGTH_SHORT).show();
            }finish();
        }
        else if (view== findViewById(R.id.cbbj)){
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            student.age= Integer.parseInt(editTextAge.getText().toString());
            student.email=editTextEmail.getText().toString();
            student.name=editTextName.getText().toString();
            student.bz=Integer.parseInt(editTextbz.getText().toString());
            student.bj=Integer.parseInt(editTextbj.getText().toString())+1;
            student.cd=Integer.parseInt(editTextcd.getText().toString());
            student.cj=Integer.parseInt(editTextcj.getText().toString());
            student.student_ID=_Student_Id;

            if (_Student_Id==0){
                _Student_Id = repo.insert(student);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(student);
                Toast.makeText(this,"学生信息修改成功",Toast.LENGTH_SHORT).show();
            }finish();
        }
        else if (view== findViewById(R.id.cbcd)){
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            student.age= Integer.parseInt(editTextAge.getText().toString());
            student.email=editTextEmail.getText().toString();
            student.name=editTextName.getText().toString();
            student.bz=Integer.parseInt(editTextbz.getText().toString());
            student.bj=Integer.parseInt(editTextbj.getText().toString());
            student.cd=Integer.parseInt(editTextcd.getText().toString())+1;
            student.cj=Integer.parseInt(editTextcj.getText().toString());
            student.student_ID=_Student_Id;

            if (_Student_Id==0){
                _Student_Id = repo.insert(student);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(student);
                Toast.makeText(this,"学生信息修改成功",Toast.LENGTH_SHORT).show();
            }finish();
        }

        else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
