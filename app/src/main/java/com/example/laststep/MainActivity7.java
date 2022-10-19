package com.example.laststep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity7 extends AppCompatActivity {
TextView temail,tuser,tphone,tpass;
EditText elocation,ehobies,estudys;
Button compleate;



    private String getExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        Intent intent = getIntent();
        getExtra =  intent.getStringExtra("Id");

        temail=(TextView) findViewById(R.id.textViewemail);
        tuser=(TextView) findViewById(R.id.textVieweusername);
        tphone=(TextView) findViewById(R.id.textViewphone);
        tpass=(TextView) findViewById(R.id.textViewpass);


//compleate data
        elocation=(EditText)findViewById(R.id.location);
        ehobies=(EditText)findViewById(R.id.hobies);
        estudys=(EditText)findViewById(R.id.studys);

        compleate=(Button)findViewById(R.id.compleate);
        compleate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             updateData();
            }
        });
        getUserInfo();
    }

    //userinfo
    private void getUserInfo(){
        temail.setText(MainActivity.u_Email);
        tuser.setText(MainActivity.username);
        tphone.setText(MainActivity.u_phone);
        tpass.setText(MainActivity.u_passw);

    }
    //updateinfo
    private void updateData() {
        Map<String,String> params =new HashMap<String,String>();



        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/finaly/update.php",
                        response -> new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = response.getJSONArray("dishs");
                            String stat =    jsonArray.getJSONObject(0).get("Stat").toString();
                            getExtra =    jsonArray.getJSONObject(0).get("Id").toString();
                            Toast.makeText(getApplicationContext(),"data updated  "+stat,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Error Response 102",Toast.LENGTH_SHORT).show();
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();
                params.put("Id",getExtra.toString());
                params.put("location",elocation.getText().toString());
                params.put("hobies",ehobies.getText().toString());
                params.put("stydys",estudys.getText().toString());

                return  params;
            }



        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }


}

   

