package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    TextView output;
    EditText search;


    public  void submit_onClick(View v){
        URL link = null;
        System.out.println("Hello");
        String outStr = "";
        String strLink = "http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_postcode&postcode=";
        String strPostCode = ""; //replace "" with the value from the ediTtext
        ArrayList<String> ListItems = new ArrayList<String>();
        try {
            link = new URL(strLink + strPostCode);
            HttpURLConnection tc = (HttpURLConnection) link.openConnection();
            InputStreamReader isr = new InputStreamReader(tc.getInputStream());
            BufferedReader in = new BufferedReader(isr);

             String line = "";
            while ((line = in.readLine()) !=null){
                 JSONArray ja = new JSONArray(line);
                for(int i = 0; i < ja.length(); i++){
                    JSONObject jo = (JSONObject) ja.get(i);
                    outStr += jo.getString("BusinessName") + ", "+jo.getString("RatingValue") + "\n";

                 }
            }
             Log.e("TAG",outStr);
            /// set value to a the text view
            output = findViewById(R.id.output);
            output.setText(outStr);







        }
        catch (MalformedURLException e){e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (JSONException e){e.printStackTrace();}
    }


}
