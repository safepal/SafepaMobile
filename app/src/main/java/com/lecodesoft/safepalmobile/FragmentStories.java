package com.lecodesoft.safepalmobile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lecodesoft.safepalmobile.jsonreport.JSONParser;
import com.lecodesoft.safepalmobile.storyrecycler.RVStoryAdapter;
import com.lecodesoft.safepalmobile.storyrecycler.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jingo on 2/29/16.
 */
public class FragmentStories  extends Fragment {

    List<Story> stories;
    RecyclerView recyclerViewStories;
    RVStoryAdapter rvStoryAdapter;
    View rootView;

    /****
     * variables for json report reading
     */

    // Progress Dialog
    //private ProgressDialog pDialog;

  private LinearLayout llFFtoriesProgress;

    //private ProgressBar pbStoriesInternet;
    private Button buttoStoriesRetry;

    private static final String READ_REPORTS_URL = "http://www.thinkitlimited.com/safepal/api/reports.php";

    // JSON IDS:

    private static final String TAG_POSTS = "posts";
    private static final String TAG_TI_LOCATION = "ti_location";
    private static final String TAG_TI_DATE = "ti_date";
    private static final String TAG_TI_TIME = "ti_time";
    private static final String TAG_TI_DESCRIPTION = "ti_description";
    private static final String TAG_TI_TYPE = "ti_type";
    private static final String TAG_TI_ACTIONTAKEN = "ti_action";

    // An array of all of our comments
    private JSONArray mReports = null;

    // manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mReportList;

    public FragmentStories() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_stories, container, false);
        recyclerViewStories = (RecyclerView) rootView.findViewById(R.id.rv_stories);

         //pbStoriesInternet = (ProgressBar)rootView.findViewById(R.id.pb_f_stories);
//        json progress bar
                llFFtoriesProgress = (LinearLayout) rootView.findViewById(R.id.ll_f_stories_Progress);


        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());

        recyclerViewStories.setLayoutManager(llm);
        new LoadReports().execute();

        return  rootView;
    }

    /**
     * Retrieves recent post data from the server.
     */
    public void updateJSONdata() {

        // Instantiate the arraylist to contain all the JSON data.
        // we are going to use a bunch of key-value pairs, referring
        // to the json element name, and the content, for example,
        // message it the tag, and "I'm awesome" as the content..

        mReportList = new ArrayList<HashMap<String, String>>();

        // Bro, it's time to power up the J parser
        JSONParser jParser = new JSONParser();
        // Feed the beast our comments url, and it spits us
        // back a JSON object. Boo-yeah Jerome.
        JSONObject json = jParser.getJSONFromUrl(READ_REPORTS_URL);

        // when parsing JSON stuff, we should probably
        // try to catch any exceptions:
        try {

            // I know I said we would check if "Posts were Avail." (success==1)
            // before we tried to read the individual posts, but I lied...
            // mComments will tell us how many "posts" or comments are
            // available
            mReports = json.getJSONArray(TAG_POSTS);

            // looping through all posts according to the json object returned
            for (int i = 0; i < mReports.length(); i++) {
                JSONObject c = mReports.getJSONObject(i);

                // gets the content of each tag
                String ti_location = c.getString(TAG_TI_LOCATION);
                String ti_date = c.getString(TAG_TI_DATE);
                String ti_time = c.getString(TAG_TI_TIME);
                String ti_description = c.getString(TAG_TI_DESCRIPTION);
                String ti_type = c.getString(TAG_TI_TYPE);
                String ti_actiontaken = c.getString(TAG_TI_ACTIONTAKEN);

                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(TAG_TI_LOCATION, ti_location);
                map.put(TAG_TI_DATE, ti_date);
                map.put(TAG_TI_TIME, ti_time);
                map.put(TAG_TI_DESCRIPTION, ti_description);
                map.put(TAG_TI_TYPE, ti_type);
                map.put(TAG_TI_ACTIONTAKEN, ti_actiontaken);
                // adding HashList to ArrayList
                mReportList.add(map);

                // annndddd, our JSON data is up to date same with our array
                // list
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the parsed data into the listview.
     */
    private void updateList() {

        stories = new ArrayList<>();

        //add items in stories from mReportList

        for (Map<String, String> rMap : mReportList) {
            stories.add(new Story(R.drawable.ic_forced_marriage_card,
                    "In " + rMap.get(TAG_TI_LOCATION),
                    "22m ago",
                    rMap.get(TAG_TI_DESCRIPTION),
                    rMap.get(TAG_TI_DATE) + " at " + rMap.get(TAG_TI_TIME),
                    "Status:" + rMap.get(TAG_TI_ACTIONTAKEN),
                    rMap.get(TAG_TI_TYPE)));
        }

        rvStoryAdapter = new RVStoryAdapter(stories);
        rvStoryAdapter.notifyDataSetChanged();
        recyclerViewStories.setAdapter(rvStoryAdapter);
    }

    public class LoadReports extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {

            // SHOW THE SPINNER WHILE LOADING FEEDS
           llFFtoriesProgress.setVisibility(View.VISIBLE);
            //pbStoriesInternet.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {


            llFFtoriesProgress.setVisibility(View.GONE);
            //pbStoriesInternet.setVisibility(View.GONE);
            updateList();
            super.onPostExecute(result);
        }
    }

    //check internet connection
    //check internet boolean
    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        if (net != null && net.isAvailable() && net.isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}
