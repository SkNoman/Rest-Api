package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeleteData extends AppCompatActivity {

    Button DeleteData;
    EditText Id;
    ProgressBar progressBar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delete Action");
        setContentView(R.layout.activity_delete_data);

        Id = findViewById(R.id.emp_id_et);
        DeleteData = findViewById(R.id.delete_data_Btn);
        progressBar = findViewById(R.id.progressbar_id_delete);


        // adding on click listener for our button.
        DeleteData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                String url = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult?emp_id="+Id.getText().toString();
                // String url = "https://apex.oracle.com/pls/apex/noman_live_schema/test/reslult?emp_id=8&emp_name=Koly&emp_designation=SQA";
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE,url,
                        response -> Toast.makeText(DeleteData.this, "Deleted Successfully!", Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(DeleteData.this, "Something Wrong Try Again!", Toast.LENGTH_SHORT).show())
                {
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map<String,String> params = new HashMap<>();
//                        params.put("emp_id",Id.getText().toString());
//                        params.put("emp_name",Name.getText().toString());
//                        params.put("emp_designation",Designation.getText().toString());
                        return params;
                    }
                };
                requestQueue = Volley.newRequestQueue(DeleteData.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}