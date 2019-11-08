package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    NewsLoader(Context context, String url) {
        super(context);
        System.out.println(url);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.fetchNewsData(mUrl);
    }
}