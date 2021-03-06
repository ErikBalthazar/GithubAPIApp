package com.erik.githubapi.apicontrol;

import android.util.Log;

import com.erik.githubapi.data.GithubRepository;
import com.erik.githubapi.data.GithubUser;
import com.erik.githubapi.jsonmanager.JSONManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 02/05/2017.
 */
public class GithubAPIManager {
    private static final String GITHUB_API_USER_SEARCH_URL =
                                            "https://api.github.com/search/users?q=";
    private static final String GITHUB_API_REPOSITORY_SEARCH_URL =
                                            "https://api.github.com/search/repositories?q=";

    public GithubAPIManager(){}

    public List<GithubUser> searchForUsers(String searchText) {
        String responseContent = callUserSearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<GithubUser> foundUsers = jsonManager.getUsersFromJSON(responseContent);
        return foundUsers;
    }

    public List<GithubRepository> searchForRepositories(String searchText) {
        String responseContent = callRepositorySearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<GithubRepository> foundRepositories = jsonManager.getRepositoriesFromJSON(responseContent);
        return foundRepositories;
    }

    public List<GithubRepository> searchForUserRepositories(String query) {
        String responseContent = callUserRepositoriesSearch(query);
        JSONManager jsonManager = new JSONManager();
        List<GithubRepository> foundRepositories = jsonManager.getUserRepositoriesFromJSON(responseContent);
        return foundRepositories;
    }

    private String readContentFromJSON(String query) {
        StringBuilder stringBuilder = null;
        try {
            URL url = new URL(query);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return "";
        }
        return stringBuilder.toString();
    }

    private String callUserSearch(String searchText) {
        return readContentFromJSON(GITHUB_API_USER_SEARCH_URL + searchText);
    }

    private String callRepositorySearch(String searchText) {
        return readContentFromJSON(GITHUB_API_REPOSITORY_SEARCH_URL + searchText);
    }

    private String callUserRepositoriesSearch(String query) {
        return readContentFromJSON(query);
    }
}
