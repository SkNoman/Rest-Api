package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    private TextView t1;
    ProgressBar progressBar;
    String active_Status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setTitle("Employee Information");
        t1 = findViewById(R.id.t1);
        progressBar = findViewById(R.id.progressbar_id);
        progressBar.setVisibility(View.GONE);

        jsonTask jTask = new jsonTask();
        jTask.execute();


    }
    public class jsonTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            progressBar.setVisibility(View.VISIBLE);
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            String emp_id,emp_name,email_id,mobile_no,is_active,description;
            Integer institute_id;

            try {
                URL url = new URL("https://apex.oracle.com/pls/apex/noman_live_schema/emp_info/");
                //URL url = new URL("https://raw.githubusercontent.com/sknoman77/jsontest/main/db.json");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                StringBuffer lastbuffer = new StringBuffer();

                while((line = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(line);
                }
                String file = stringBuffer.toString();
                JSONObject fileObject = new JSONObject(file);
                JSONArray jsonArray = fileObject.getJSONArray("items");
                for(int i=0; i<jsonArray.length();i++)
                {
                    JSONObject arrayObject = jsonArray.getJSONObject(i);

                    emp_name = arrayObject.getString("emp_name");
                    email_id = arrayObject.getString("email_id");
                    mobile_no = arrayObject.getString("mobile_no");
                    emp_id = arrayObject.getString("emp_id");
                    is_active = arrayObject.getString("is_active");
                    institute_id = arrayObject.getInt("institute_id");
                    description = arrayObject.getString("description");
                    lastbuffer.append("Name:"+emp_name+"\n"+"Id:"+emp_id+"\n"+"Phone:"+mobile_no+"\n"+"Email:"+email_id+"\n"+"Is Active:"+is_active+"\n"+"Description:"+description+"\n\n");
                }
                return lastbuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(final String s)
        {
            super.onPostExecute(s);
            t1.setText(s);
            progressBar.setVisibility(View.GONE);
        }
    }
}


