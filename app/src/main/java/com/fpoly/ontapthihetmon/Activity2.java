package com.fpoly.ontapthihetmon;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ArrayList<StudentModels> list = new ArrayList<>();
    public static  String KEY_FILE = "student.txt";
    ListView listView;
    TextView addstd;
    ActivityResultLauncher<Intent> getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();

                        StudentModels addSTD = (StudentModels) intent.getSerializableExtra("HS");
                        list.add(addSTD);
                        setAdapterSTD();
                        ghiFile();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        docFile();
        listView = findViewById(R.id.list_std);
        addstd = findViewById(R.id.addNewStd);
        if (list.size() == 0){
            list.add(new StudentModels("Nguyen Viet Anh", 18));
            list.add(new StudentModels("Nguyen Viet Anh", 18));
            list.add(new StudentModels("Nguyen Viet Anh", 18));
        }

        setAdapterSTD();
ghiFile();
        addstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getApplicationContext(),AtvUpdate.class);
                getdata.launch(intent);
            }
        });

    }

    private void setAdapterSTD() {

        StudentAdapter studentAdapter = new StudentAdapter(this, list);
        listView.setAdapter(studentAdapter);
    }

    ActivityResultLauncher<Intent> getDataUpdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
               if (result.getResultCode() == RESULT_OK){
                   Intent intent = result.getData();
                   StudentModels updateStd = (StudentModels) intent.getSerializableExtra("HS");
                   updateStudent.setName(updateStd.getName());
                   updateStudent.setTuoi(updateStd.getTuoi());
                   setAdapterSTD();
                   ghiFile();
               }
                }
            });
    StudentModels updateStudent;
    public void updateSTD(int position){
        Intent intent = new Intent(getApplicationContext(),AtvUpdate.class);
        updateStudent= list.get(position);
        intent.putExtra("UPDATESTD",updateStudent);
       getDataUpdate.launch(intent);
    }

    public  void ghiFile(){
        try {
            FileOutputStream fos = openFileOutput(KEY_FILE,MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void docFile(){
        try {
            FileInputStream fis = openFileInput(KEY_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<StudentModels>) ois.readObject();
            fis.close();
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}