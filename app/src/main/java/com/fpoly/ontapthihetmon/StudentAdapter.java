package com.fpoly.ontapthihetmon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    Activity atv;
    ArrayList<StudentModels> list;

    public StudentAdapter(Activity atv, ArrayList<StudentModels> list) {
        this.atv = atv;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = atv.getLayoutInflater();

        convertView = inflater.inflate(R.layout.edit_listview, parent, false);
        StudentModels std = list.get(position);

        TextView txtName = convertView.findViewById(R.id.txt_name);
        TextView txtAge = convertView.findViewById(R.id.txt_age);

        ImageView bin = convertView.findViewById(R.id.bin);
        ImageView pencil = convertView.findViewById(R.id.pencil);

        txtName.setText(std.getName());
        txtAge.setText(std.getTuoi()+"");

        bin.setImageResource(R.mipmap.trash);
        pencil.setImageResource(R.mipmap.pencil);

        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(atv);
                builder.setMessage("Do you want delete this student ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   list.remove(position);
                   notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity2)atv).updateSTD(position);
            }
        });
        return convertView;
    }
}
