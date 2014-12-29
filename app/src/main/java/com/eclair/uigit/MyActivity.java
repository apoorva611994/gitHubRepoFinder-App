package com.eclair.uigit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Thread logoTimer = new Thread(){
          public void run(){
              try{
                  int logoTimer = 0;
                  while(logoTimer<5000){
                      sleep(100);
                      logoTimer=logoTimer+100;
                  }
                  startActivity(new Intent("com.eclair.uigit.CLEARSCREEN"));
              } catch (InterruptedException e) {
                  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
              } finally {
                  finish();
              }
          }
        };
        logoTimer.start();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }
}
