package com.eclair.uigit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

public class Find1 extends Activity {
    /**
     * Called when the activity is first created.
     */
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);




    }
    public void sendMessage(View view){
        Intent i = new Intent(this, MyAct.class);
        EditText textView = (EditText) findViewById(R.id.edit_text);
        String s1 = textView.getText().toString();

        if(s1.trim().length() == 0){
            Toast.makeText(getApplicationContext(), "Please Enter A Valid Username",
                    Toast.LENGTH_LONG).show();
        }
        else {

            String getrec = textView.getText().toString();


            i.putExtra("username", getrec);


            startActivity(i);
            overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        }
        }




    }
