package com.example.newsapp;

import android.text.TextUtils;
import android.util.Log;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
final class QueryUtils {

    private static final String LOG_TAG = "News";

    private static final String response = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":25830,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":2583,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2019/nov/04/tv-election-debates-must-include-the-lib-dems-leader\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2019-11-04T17:52:51Z\",\"webTitle\":\"TV election debates must include the Lib Dems’ leader | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2019/nov/04/tv-election-debates-must-include-the-lib-dems-leader\",\"apiUrl\":\"https://content.guardianapis.com/politics/2019/nov/04/tv-election-debates-must-include-the-lib-dems-leader\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2019/oct/30/liberal-democrats-face-being-frozen-out-of-live-tv-debates-general-election\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2019-10-30T19:00:46Z\",\"webTitle\":\"Lib Dems face being frozen out of any live TV debates\",\"webUrl\":\"https://www.theguardian.com/politics/2019/oct/30/liberal-democrats-face-being-frozen-out-of-live-tv-debates-general-election\",\"apiUrl\":\"https://content.guardianapis.com/politics/2019/oct/30/liberal-democrats-face-being-frozen-out-of-live-tv-debates-general-election\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/live/2019/oct/30/lords-to-debate-boris-johnsons-early-election-bill-after-brexit-deadlock-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2019-10-30T18:04:01Z\",\"webTitle\":\"General election: Corbyn challenges Johnson to TV debates - as it happened\",\"webUrl\":\"https://www.theguardian.com/politics/live/2019/oct/30/lords-to-debate-boris-johnsons-early-election-bill-after-brexit-deadlock-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/live/2019/oct/30/lords-to-debate-boris-johnsons-early-election-bill-after-brexit-deadlock-live\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/sep/24/france-debates-law-lesbians-single-women-ivf\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-09-24T16:11:10Z\",\"webTitle\":\"France debates law to let lesbians and single women have IVF\",\"webUrl\":\"https://www.theguardian.com/world/2019/sep/24/france-debates-law-lesbians-single-women-ivf\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/sep/24/france-debates-law-lesbians-single-women-ivf\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"us-news/2019/jul/31/healthcare-democrats-2020-debates-detroit\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2019-07-31T19:39:21Z\",\"webTitle\":\"Democratic debates: how healthcare is defining and dividing 2020 candidates\",\"webUrl\":\"https://www.theguardian.com/us-news/2019/jul/31/healthcare-democrats-2020-debates-detroit\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2019/jul/31/healthcare-democrats-2020-debates-detroit\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2019/may/11/missing-ingredient-debates-generosity-labour-antisemitism\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2019-05-11T05:00:12Z\",\"webTitle\":\"The missing ingredient in today’s debates? Generosity | Gary Younge\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2019/may/11/missing-ingredient-debates-generosity-labour-antisemitism\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2019/may/11/missing-ingredient-debates-generosity-labour-antisemitism\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"us-news/2019/jun/26/democratic-debate-2019-watch-2020-election-when-where-who\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2019-06-27T20:23:02Z\",\"webTitle\":\"Democratic debates 2019: everything you need to know\",\"webUrl\":\"https://www.theguardian.com/us-news/2019/jun/26/democratic-debate-2019-watch-2020-election-when-where-who\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2019/jun/26/democratic-debate-2019-watch-2020-election-when-where-who\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"us-news/2019/sep/10/presidential-debates-2020-houston-gun-control\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2019-09-10T06:00:42Z\",\"webTitle\":\"As Houston readies to host the next 2020 debates, focus turns to gun control\",\"webUrl\":\"https://www.theguardian.com/us-news/2019/sep/10/presidential-debates-2020-houston-gun-control\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2019/sep/10/presidential-debates-2020-houston-gun-control\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2019/jun/29/democratic-debate-kamala-harris-elizabeth-warren-trump\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2019-06-29T10:00:46Z\",\"webTitle\":\"Who won the Democratic debates? Kamala Harris, Elizabeth Warren – and Trump | Cas Mudde\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2019/jun/29/democratic-debate-kamala-harris-elizabeth-warren-trump\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2019/jun/29/democratic-debate-kamala-harris-elizabeth-warren-trump\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"us-news/2019/jul/18/democratic-debates-biden-harris-bernie-warren-2020\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2019-07-19T01:45:58Z\",\"webTitle\":\"Democratic debates: Biden and Harris will face off again in second round\",\"webUrl\":\"https://www.theguardian.com/us-news/2019/jul/18/democratic-debates-biden-harris-bernie-warren-2020\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2019/jul/18/democratic-debates-biden-harris-bernie-warren-2020\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link NewsData} objects.
     */
    static List<NewsData> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * Return a list of {@link NewsData} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<NewsData> extractFeatureFromJson(String jsonObj) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonObj)) {
            return null;
        }

        List<NewsData> news = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonObj);

            JSONObject jsonObject = baseJsonResponse.getJSONObject("response");

            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject currentarticle = jsonArray.getJSONObject(i);

                // sectionName , webTitle , webPublicationDate
                String sectionName = currentarticle.getString("sectionName");

                String webTitle = currentarticle.getString("webTitle");

                String webPublicationDate = currentarticle.getString("webPublicationDate");

                String webUrl = currentarticle.getString("webUrl");

                NewsData newsData = new NewsData(sectionName, webTitle, webPublicationDate ,webUrl);
                news.add(newsData);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of news
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
