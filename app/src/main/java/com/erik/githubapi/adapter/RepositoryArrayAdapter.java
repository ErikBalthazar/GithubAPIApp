package com.erik.githubapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erik.githubapi.R;
import com.erik.githubapi.data.GithubRepository;

import java.util.List;

/**
 * Created by Erik on 02/05/2017.
 */
public class RepositoryArrayAdapter extends ArrayAdapter<GithubRepository> {
    public RepositoryArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public RepositoryArrayAdapter(Context context, int resource, List<GithubRepository> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repository_list_row, null);
        }

        GithubRepository repository = getItem(position);

        if (repository != null) {
            TextView repositoryName = (TextView) convertView.findViewById(R.id.repository_name_text);
            TextView repositoryOwnerName = (TextView) convertView.findViewById(R.id.repository_owner_name_text);
            repositoryName.setText(repository.getName());
            repositoryOwnerName.setText(repository.getOwnerName());
        }

        return convertView;
    }
}
