package com.example.astatic.newsapp.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by static on 6/21/2017.
 *
 * https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=c8ef2db562e049c799cdf9bd1e3cf15b
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://newsapi.org/v1/articles";

    private static final String source = "the-next-web";

    private static final String sort = "latest";

    private static final String Key = "c8ef2db562e049c799cdf9bd1e3cf15b";


    final static String QUERY_PARAM = "q";
      final static String source_PARAM = "source";
    final static String sortBy_PARAM = "sortBy";
    final static String APIKEY_PARAM = "apiKey";

    public static URL buildUrl(String keys) {


        Uri builtUri = Uri.parse(BASE_URL).buildUpon()

                .appendQueryParameter(source_PARAM, source)
                .appendQueryParameter(sortBy_PARAM, sort)
                .appendQueryParameter(APIKEY_PARAM, keys)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

//builder.scheme("https")
//        .authority("www.newsapi.org")
//    .appendPath("turtles")
//    .appendPath("types")
//    .appendQueryParameter("type", "1")
//    .appendQueryParameter("sort", "relevance")
//    .fragment("section-name");
//    String myUrl = builder.build().toString();
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}
