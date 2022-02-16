package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostData extends AppCompatActivity {

    EditText Id,Name,Designation;
    Button InsertData,Update_data_activity_Btn;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);
        setTitle("Insert Action");

        Id = findViewById(R.id.emp_id_et);
        Name = findViewById(R.id.emp_name_et);
        Designation = findViewById(R.id.emp_designation_et);
        InsertData = findViewById(R.id.insert_data_Btn);
        InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult";
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        response -> Toast.makeText(PostData.this, "Inserted Successfully!", Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(PostData.this, "Something Wrong Try Again!", Toast.LENGTH_SHORT).show()){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("emp_id",Id.getText().toString());
                        params.put("emp_name",Name.getText().toString());
                        params.put("emp_designation",Designation.getText().toString());
                        return params;
                    }
                };
                requestQueue = Volley.newRequestQueue(PostData.this);
                requestQueue.add(stringRequest);



            }
        });
    }
}