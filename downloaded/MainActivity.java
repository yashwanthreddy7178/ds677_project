package com.example.rbrazuk.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    final List<String> taskArray = new ArrayList<>();
    private ListView taskList;
    private ArrayAdapter<String> arrayAdapter;

    public void addTask(View view){
        EditText taskInput = (EditText) findViewById(R.id.taskInput);

        String newTask = taskInput.getText().toString();

        taskArray.add(newTask);
        taskInput.setText("");

        ((LinearLayout) findViewById(R.id.dummy_id)).requestFocus();

        taskInput.requestFocus();

        arrayAdapter.notifyDataSetChanged();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = (ListView) findViewById(R.id.tasksList);



        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,taskArray);
        taskList.setAdapter(arrayAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String delete = taskArray.get(position);
                System.out.println(position);

                int index = taskArray.indexOf(delete);

                taskArray.remove(index);

                arrayAdapter.notifyDataSetChanged();

            }
        });

        ((LinearLayout) findViewById(R.id.dummy_id)).requestFocus();





    }
}
