package com.erik.githubapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserSearchActivity extends AppCompatActivity {

    private EditText mSearchText;
    private Button mSearchButton;
    private TextView mTestView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        mSearchText = (EditText) findViewById(R.id.user_search_text);
        mSearchButton = (Button) findViewById(R.id.user_search_search_button);
        mTestView = (TextView) findViewById(R.id.test_view);
        mProgressBar = (ProgressBar) findViewById(R.id.user_search_progressbar);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserSearchTask().execute(mSearchText.getText().toString());
            }
        });
    }

    private class UserSearchTask extends AsyncTask<String, Void, String> {
        static final String GITHUB_API_URL = "https://api.github.com/search/users?q=";

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mTestView.setText("");
        }

        protected String doInBackground(String... params) {
            String searchText = params[0];

            try {
                URL url = new URL(GITHUB_API_URL + searchText);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "Algo saiu errado";
            }
            mProgressBar.setVisibility(View.GONE);
            Log.i("RESPONSE", response);
            mTestView.setText(response);
        }
    }
}
