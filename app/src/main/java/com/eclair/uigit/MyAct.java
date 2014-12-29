package com.eclair.uigit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MyAct extends Activity {

    TextView textName;
    TextView textName2;
    ListView listView;
    Context context;
    ProgressBar progressBar;
    static ArrayList<String> urls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        context = this;
        String s = getIntent().getStringExtra("username");

        // get reference to the views
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textName = (TextView) findViewById(R.id.textView);
        textName2 = (TextView) findViewById(R.id.textView2);
        listView = (ListView) findViewById(R.id.listView);

        // check if you are connected or not
        if (isConnected()) {
            textName.append(s + ":");
            // call AsynTask to perform network operation on separate thread
            new HttpAsyncTask().execute("https://api.github.com/users/" + s
                    + "/repos");

        } else {
            progressBar.setVisibility(View.INVISIBLE);
            textName.append("Please Connect To The Internet");
            Toast.makeText(getBaseContext(), "No Connection!", Toast.LENGTH_LONG)
                    .show();
        }







    }


    public static String[] GET(String url) {
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder("");
        String[] repos = null;
        urls = new ArrayList<String>();

        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                //Create Json Array
                JSONArray reps = new JSONArray(
                        convertInputStreamToString(inputStream));
                String[] Repos = new String[reps.length()];
                for (int i = 0; i < reps.length(); i++) {
                    //Create JSON Object
                    JSONObject item = (JSONObject) reps.get(i);
                    StringBuilder rslt = new StringBuilder("");



                            urls.add(item.get("html_url").toString());

                            rslt.append("\n(").append(i + 1).append(") ")
                                    .append(item.get("name")).append(":\n")
                                    .append(item.get("description")).append("\n")
                                    .append(item.get("html_url")).append("\n")
                                    .append("Stars:").append(item.get("stargazers_count")).append("\n")
                                    .append("Forks:").append(item.get("forks_count")).append("\n")
                                    .append("Is The Repository A Fork: ").append(item.get("fork")).append("\n");
                            result.append(item.get("url"));
                            Repos[i] = rslt.toString();





                }
                repos = Repos;
            } else {
                result.append("Did not work!");
            }

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return repos;

    }
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }



    private class HttpAsyncTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... url2) {
            String[] emty = new String[50];

                return GET(url2[0]);

        }



        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String[] result) {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG)
                        .show();
                listView.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, result));


                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                        Toast.makeText(getApplicationContext(), urls.get(i),
                                Toast.LENGTH_LONG).show();
                        Intent x = new Intent(Intent.ACTION_VIEW);
                        x.setData(Uri.parse(urls.get(i)));
                        startActivity(x);


                    }


                });
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Username Not Found!",
                        Toast.LENGTH_LONG).show();
                textName2.setText("Username Not Found!");

            }

        };
        // etResponse.setText(result);
    }
}


