package com.example.laststep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    EditText Email1,pass1,conpass,phone,user;
    Button signup;

    public static String location ;
    public static String hobies ;
    public static String studys ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        user=(EditText)findViewById(R.id.username1);
        Email1=(EditText)findViewById(R.id.email1);
        phone=(EditText)findViewById(R.id.phone1);
        pass1=(EditText)findViewById(R.id.Password1);
        conpass=(EditText)findViewById(R.id.conPassword2);
        phone=(EditText)findViewById(R.id.phone1);
        signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().trim().isEmpty()) {

                    user.setError("username Filed should not be empty!");
                    user.requestFocus();
                }

                if (Email1.getText().toString().trim().isEmpty()) {

                    Email1.setError("Email Filed should not be empty!");
                    Email1.requestFocus();
                }
                if (phone.getText().toString().trim().isEmpty()) {

                    phone.setError("Phone number Filed should not be empty!");
                    phone.requestFocus();
                }
                if (pass1.getText().toString().trim().isEmpty()) {

                    pass1.setError("password Filed should not be empty!");
                    pass1.requestFocus();}
                if(!(pass1.getText().toString().equals(conpass.getText().toString()))){
                    conpass.setError("passwords not equal!!");
                    conpass.requestFocus();
                }

                else {
                    sendData();


                }
            }

        });
            }





    private void sendData() {
        Map<String,String> params =new HashMap<String,String>();

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/finaly/create.php",response -> new Response.Listener<JSONObject>() {

                  @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("dishs");
                            String stat =    jsonArray.getJSONObject(0).get("status").toString();

                            Toast.makeText(getApplicationContext(),"Welcome User :) status =  "+stat,Toast.LENGTH_SHORT).show();

                            if(stat.matches("ok")){
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
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

                params.put("username",user.getText().toString());
                params.put("Email",Email1.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("password",pass1.getText().toString());

                return  params;
            }

        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }
}