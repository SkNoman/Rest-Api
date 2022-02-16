package com.example.restapi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateData extends AppCompatActivity
{

    EditText Id, Name, Designation;
    Button InsertData;
    private ProgressBar progressBar;
    private TextView responseTV;
    RequestQueue requestQueue;
    public static final String MAIN_URL = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Update Action");
        setContentView(R.layout.activity_update_data);
        Id = findViewById(R.id.emp_id_et);
        Name = findViewById(R.id.emp_name_et);
        Designation = findViewById(R.id.emp_designation_et);
        InsertData = findViewById(R.id.insert_data_Btn);
        progressBar = findViewById(R.id.progressbar_id_update);


        // adding on click listener for our button.
        InsertData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                String url = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult";
                // String url = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult?emp_id=8&emp_name=Koly&emp_designation=SQA";
                StringRequest stringRequest = new StringRequest(Request.Method.PUT,url,
                        response -> Toast.makeText(UpdateData.this, "Updated Successfully!", Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(UpdateData.this, "Something Wrong Try Again!", Toast.LENGTH_SHORT).show())
                {
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map<String,String> params = new HashMap<>();
                        params.put("emp_id",Id.getText().toString());
                        params.put("emp_name",Name.getText().toString());
                        params.put("emp_designation",Designation.getText().toString());
                        return params;
                    }
                };
                requestQueue = Volley.newRequestQueue(UpdateData.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}


