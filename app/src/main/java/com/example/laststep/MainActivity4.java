package com.example.laststep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {
    ListView listView;
    ArrayList<String> Arraynews = new ArrayList<String>();
    ArrayList<String> Arrayallnews = new ArrayList<String>();
    String [] Arraynew,Arrayallnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView= (ListView) findViewById(R.id.listsport);
        getData();

    }
    class adapter extends ArrayAdapter<String> {

        Context context;
        String[] array1, array2;


        public adapter(Context c, String[] v1, String[] v2) {
            super(c, R.layout.items, R.id.textnews, v1);

            this.context = c;
            this.array1 = v1;
            this.array2 = v2;

        }
        @Override

        public View getView(int position, View v, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.items, parent, false);


            TextView tx1 = row.findViewById(R.id.textnews);
            TextView tx2 = row.findViewById(R.id.textallnew);
            tx1.setText(array1[position]);
            tx2.setText(array2[position]);
            return row;
        }
}
    private void getData() {


        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/finaly/sports.php", null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("dishs");

                            //Toast.makeText(getApplicationContext(), " json Array length = " +jsonArray.length(), Toast.LENGTH_SHORT).show();
                            Arraynew = new String[jsonArray.length()];
                            Arrayallnew = new String[jsonArray.length()];

                            for(int i = 0 ; i <= jsonArray.length() ; i++){


                                String newsname = jsonArray.getJSONObject(i).get("newsname").toString();
                                String allnews = jsonArray.getJSONObject(i).get("allnews").toString();

                                Arraynew [i] = newsname;
                                Arrayallnew [i] = allnews;





                                // do when the data all reseved
                                if( i == jsonArray.length() -1){


                                    // Toast.makeText(getApplicationContext(), "Array List Names = " +ArrayNames.length, Toast.LENGTH_SHORT).show();
                                    // pass the data to the adapter
                                    adapter adp1 = new adapter(getApplicationContext(),Arraynew,Arrayallnew);
                                    listView.setAdapter(adp1);
                                }
// by prog hazem ahmad   <<< www.beprogrammer.org >>> ...
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error Response In PHP File ", Toast.LENGTH_SHORT).show();

                    }
                }

                ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();

                // you must have in the php file a post method to get this data
                // ex : $_POST['DataToSend'];
                return params;
            }

        };
// Access the RequestQueue through your singleton class.
        rq.getCache().clear();
        rq.add(jsonObjectRequest);

    }





}