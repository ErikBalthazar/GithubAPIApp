package com.erik.githubapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erik.githubapi.R;
import com.erik.githubapi.data.GithubRepository;

import java.util.List;

/**
 * Created by Erik on 02/05/2017.
 */
public class UserRepositoriesArrayAdapter extends ArrayAdapter<GithubRepository> {
    public UserRepositoriesArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public UserRepositoriesArrayAdapter(Context context, int resource, List<GithubRepository> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_repositories_list_row, null);
        }

        GithubRepository repository = getItem(position);

        if (repository != null) {
            TextView userRepositoryName = (TextView) convertView.findViewById(R.id.user_repository_name_text);
            userRepositoryName.setText(repository.getName());
        }

        return convertView;
    }
}
