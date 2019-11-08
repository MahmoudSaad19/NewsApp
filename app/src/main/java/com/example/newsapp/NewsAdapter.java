package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsData> {

    /**
     * @param context of the app
     * @param newsData is the list of newsData, which is the data source of the adapter
     */
    NewsAdapter(Context context, List<NewsData> newsData) {
        super(context, 0, newsData);
    }

    /**
     * Returns a list item view that displays information about the given position
     * in the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        NewsData newsData = getItem(position);

        TextView section_textView = listItemView.findViewById(R.id.section_name);

        section_textView.setText(newsData.getSectionName());

        TextView title_textView = listItemView.findViewById(R.id.title);

        title_textView.setText(newsData.getWebTitle());

        TextView time_textView = listItemView.findViewById(R.id.time);

        time_textView.setText(newsData.getWebPublicationDate());

        TextView author_textView = listItemView.findViewById(R.id.author);

        author_textView.setText(newsData.getContributor());

        return listItemView;
    }
}
