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

public class MainActivity extends AppCompatActivity {
    EditText Email, pass;

    Button log;
    TextView creat;
    private String uID ;
    public static String username ;
    public static String u_Email ;
    public static String u_phone ;
    public static String u_passw ;

//regestration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creat = (TextView) findViewById(R.id.creat);
        Email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.Password);
        log = (Button) findViewById(R.id.log);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getText().toString().trim().isEmpty()) {

                    Email.setError("your email Filed should not be empty!");
                    Email.requestFocus();
                }

                if (pass.getText().toString().trim().isEmpty()) {

                    pass.setError("password Filed should not be empty!");
                    pass.requestFocus();
                } else {
                    signin();
                }
            }
        });
        creat = (TextView) findViewById(R.id.creat);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(in);

            }
        });
    }

    private void signin() {
        Map<String, String> params = new HashMap<String, String>();


        RequestQueue rq = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/finaly/login.php?Email=" + Email.getText().toString()
                        + "&&" + "password=" + pass.getText().toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("dishs");

                            String stat = jsonArray.getJSONObject(0).get("Stat").toString();

                            uID = jsonArray.getJSONObject(0).get("Id").toString();

                            Toast.makeText(getApplicationContext(), "Welcome User :) stat =  " + stat, Toast.LENGTH_SHORT).show();

                            if (stat.matches("1")) {

                                Toast.makeText(getApplicationContext(), "User was Founded :)  ", Toast.LENGTH_SHORT).show();
                                 //Log.e("UserID: " , )   ;
                                u_Email = jsonArray.getJSONObject(0).get("Email").toString();
                                username = jsonArray.getJSONObject(0).get("username").toString();
                                u_passw = jsonArray.getJSONObject(0).get("password").toString();
                                u_phone = jsonArray.getJSONObject(0).get("phone").toString();
                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                intent.putExtra("Id", jsonArray.getJSONObject(0).get("Id").toString());
                                startActivity(intent);


                            } else {

                                Toast.makeText(getApplicationContext(), "No User yet please create new account!!!  ", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error Response 102" + error, Toast.LENGTH_SHORT).show();

                    }
                }
                ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Email", Email.getText().toString());
                params.put("password", pass.getText().toString());

                return params;
            }


        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }


}