package com.eclair.uigit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

    }
    public void viewsch(View view)
    {
        Intent myIntent = new Intent(Menu.this, Find1.class);
        //myIntent.putExtra("key", value); //Optional parameters
        Menu.this.startActivity(myIntent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
    }


    }


