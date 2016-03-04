package com.vatsal.android.carx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo on 2/25/2016.
 */
public class DriverScoreActivity extends AppCompatActivity {
    private String[] mSt;
    private TextView mHard_Breaking;
    private TextView mRapid_Accels;
    private TextView mSpeeding;
    private String mContent;
    private TextView mIdle_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driverscore);

        init();

        new ReadFile().execute();
    }

    private void init() {

        mHard_Breaking = (TextView) findViewById(R.id.Hard_Breaking);
        mRapid_Accels = (TextView) findViewById(R.id.Rapid_Accels);
        mSpeeding = (TextView) findViewById(R.id.Speeding);
        mIdle_Time= (TextView)findViewById(R.id.Idle_Time);

    }

    private void updateUI() {


        mHard_Breaking.setText("Hard Breaking:"+mSt[0]);
        mRapid_Accels.setText("Rapid Accels:"+mSt[1]);
        mSpeeding.setText("Speeding:"+mSt[2]);
        mIdle_Time.setText("Idle_Time:"+mSt[3]);

    }


    private class ReadFile extends AsyncTask<Void, Void, String> {

        private static final String
                URL_STRING = "http://52.18.186.202/kushaldata/read_file.php",
                JSON_S_CONTENT = "content";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(URL_STRING);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                int responseCode = connection.getResponseCode();

                StringBuilder builder = new StringBuilder();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    br.close();
                }
                connection.disconnect();
                return builder.toString();

            } catch (Exception e) {
                return null;
            }
        }


        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                try {
                    Log.d(getClass().getSimpleName(), s);
                    JSONObject object = new JSONObject(s);
                    mContent = object.getString(JSON_S_CONTENT);
                    mSt = mContent.split(",");

                    updateUI();
                } catch (JSONException e) {
                    return;
                }
            }
        }
    }

}

