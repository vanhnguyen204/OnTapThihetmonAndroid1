package com.fpoly.ontapthihetmon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtvUpdate extends AppCompatActivity {

    EditText updateName;
    EditText updateAge;
    Button btnConfirm;
    Button btnCancel;
    StudentModels studentModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_update);

        updateName = findViewById(R.id.updatename);
        updateAge = findViewById(R.id.updateAge);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);
        studentModels = (StudentModels) getIntent().getSerializableExtra("UPDATESTD");
        if (studentModels != null) {

            updateName.setText(studentModels.getName());
            updateAge.setText(studentModels.getTuoi() + "");
        }


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String   age = updateAge.getText().toString();
                String ten = updateName.getText().toString();
                int updateAgenew;
                try {

                     updateAgenew = Integer.parseInt(age);

                    if (age.length() == 0) {
                        Toast.makeText(AtvUpdate.this, "Ban chua nhap ten", Toast.LENGTH_SHORT).show();
                    } else if (ten.length() == 0) {
                        Toast.makeText(AtvUpdate.this, "Ban chua nhap tuoi", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent();
                        StudentModels studentModels = new StudentModels(ten, updateAgenew);

                        intent.putExtra("HS", studentModels);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(AtvUpdate.this, "Tuoi phai la so", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}