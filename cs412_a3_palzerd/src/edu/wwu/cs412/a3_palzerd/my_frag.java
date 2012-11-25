package edu.wwu.cs412.a3_palzerd;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class my_frag extends Fragment{
    private Main myActivity = null;
    int mCurCheckPosition = 0;
    
    public class Tweet {
    	public String username;
    	public String message;
    	public String img_url;
    	public Tweet (String username, String message, String img_url){
    		this.username = username;
    		this.message = message;
    		this.img_url = img_url;
    	}
    }
    
    @Override
    public void onInflate(AttributeSet attrs, Bundle icicle) {
    	Log.v(Main.TAG,
    			"in TitlesFragment onInflate. AttributeSet contains:");
    	for(int i=0; i<attrs.getAttributeCount(); i++) {
            Log.v(Main.TAG, "    " + attrs.getAttributeName(i) +
            		" = " + attrs.getAttributeValue(i));
            		
    	}
    	super.onInflate(attrs, icicle);
/*    	
 *      See defect 14796. onInflate() is getting called after onCreateView()
 *      when the orientation changes from landscape to portrait. If we try
 *      to set arguments, we get an IllegalStateException.
    	Bundle bundle = this.getArguments();
    	if(bundle == null) bundle = new Bundle();
    	bundle.putString("Dave", "Dave");
    	this.setArguments(bundle);
 */
    }

    private void setOnClickListeners(View v)
    {
    	Log.w(Main.TAG, "Set onclick listeners.");
    	Button searchButton = (Button) v.findViewById(R.id.search_button);
    	Button moreButton = (Button) v.findViewById(R.id.more_button);
    	
    	searchButton.setOnClickListener(my_click_listener);
    	moreButton.setOnClickListener(my_click_listener);
    }
    
    @Override
    public void onAttach(Activity myActivity) {
    	Log.v(Main.TAG, "in TitlesFragment onAttach; activity is: " + myActivity);
    	super.onAttach(myActivity);
    	this.myActivity = (Main)myActivity;
    }

    @Override
    public void onCreate(Bundle icicle) {
    	Log.v(Main.TAG, "in TitlesFragment onCreate. Bundle contains:");
    	if(icicle != null) {
            for(String key : icicle.keySet()) {
                Log.v(Main.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(Main.TAG, "    myBundle is null");
    	}   	
    	super.onCreate(icicle);
        if (icicle != null) {
            // Restore last state for checked position.
            mCurCheckPosition = icicle.getInt("curChoice", 0);
        }
        
    }

    @Override
    public View onCreateView(LayoutInflater myInflater, ViewGroup container, Bundle icicle) {
    	Log.v(Main.TAG, "in TitlesFragment onCreateView. container is " + container);
    	View view = myInflater.inflate(R.layout.fragment_ui, container);
    	
    	setOnClickListeners(view);
    	return view;
    	//return super.onCreateView(myInflater, container, icicle);
    }
    
    @Override
    public void onActivityCreated(Bundle icicle) {
    	Log.v(Main.TAG, "in TitlesFragment onActivityCreated. icicle contains:");
    	if(icicle != null) {
            for(String key : icicle.keySet()) {
                Log.v(Main.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(Main.TAG, "    icicle is null");
    	}
        super.onActivityCreated(icicle);

        // Populate list with our static array of titles.
               
        ListView lv = (ListView) getView().findViewById(R.id.listView1);
        
        lv.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                Shakespeare.TITLES));
        
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mCurCheckPosition);

        //myActivity.showDetails(mCurCheckPosition);
    }

    @Override
    public void onStart() {
    	Log.v(Main.TAG, "in TitlesFragment onStart");
    	super.onStart();
    }

    @Override
    public void onResume() {
    	Log.v(Main.TAG, "in TitlesFragment onResume");
    	super.onResume();
    }

    @Override
    public void onPause() {
    	Log.v(Main.TAG, "in TitlesFragment onPause");
    	super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle icicle) {
    	Log.v(Main.TAG, "in TitlesFragment onSaveInstanceState");
        super.onSaveInstanceState(icicle);
        icicle.putInt("curChoice", mCurCheckPosition);
    }

    //@Override
    //public void onListItemClick(ListView l, View v, int pos, long id) {
    //	Log.v(Main.TAG, "in TitlesFragment onListItemClick. pos = "
    //			+ pos);
    //    //myActivity.showDetails(pos);
    //	mCurCheckPosition = pos;
    //}

    @Override
    public void onStop() {
    	Log.v(Main.TAG, "in TitlesFragment onStop");
    	super.onStop();
    }

    @Override
    public void onDestroyView() {
    	Log.v(Main.TAG, "in TitlesFragment onDestroyView");
    	super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    	Log.v(Main.TAG, "in TitlesFragment onDestroy");
    	super.onDestroy();
    }

    @Override
    public void onDetach() {
    	Log.v(Main.TAG, "in TitlesFragment onDetach");
    	super.onDetach();
    	myActivity = null;
    }
    
    OnClickListener my_click_listener = new OnClickListener() {
	    public void onClick(View clickedView)
	    {
	    	switch(clickedView.getId())
	    	{
	    	case R.id.search_button:
	    		Log.w(Main.TAG, "Firing Search.");
	    		FragmentManager fm = getFragmentManager();
	    		search_diag my_search_diag = new search_diag();
	    		my_search_diag.show(fm, "search frag");
	    	//	submitLoginInfo();
	    		break;
	    	case R.id.more_button:
	    		Log.w(Main.TAG, "Firing More.");
	    	//	createAccount();
	    		break;
	    	default:
	    		Log.w(Main.TAG, "Firing unimplemented button event.");
	    	}
	    }
    };
    
    public ArrayList<Tweet> do_search(String search_string) throws JSONException{
    	Log.w(Main.TAG, "Going to search twitter with: " + search_string);
    	String search_url = "http://search.twitter.com/search.json?q=@" + search_string;
    	Log.w(Main.TAG, "Going to search twitter with url: " + search_url);
    	
    	ArrayList <Tweet> tweets = new ArrayList <Tweet>();
    	
    	HttpClient client = new DefaultHttpClient();
    	HttpGet get = new HttpGet(search_url);
    	
    	ResponseHandler<String> responseHandler = new BasicResponseHandler();
    	
    	String responseBody = "not changed";
    	try{
    		Log.v("TEST","going execute client with: " + get + ", and: " + responseHandler);
    	  //responseBody = client.execute(get,responseHandler);
    		responseBody = client.execute(get,responseHandler);
    	} catch(Exception ex){
    		Log.v("TEST","Exception: " + ex);
    	  //ex.printStackTrace();
    	}
    	
    	JSONObject jsonObject = null;
    	JSONParser parser = new JSONParser();
    	
    	try {Log.v("TEST","going to parse results from response: " + responseBody);
    	  Object obj = parser.parse(responseBody);
    	  Log.v("TEST","going to put results in jsonobject");
    	  jsonObject=(JSONObject)obj;
    	}catch(Exception ex){
    	  Log.v("TEST","Exception: " + ex.getMessage());
    	}
    	   
    	JSONArray arr = null;
    	
    	
    	
    	try {Log.v("TEST","going to get results from obj");
    	  Object j = jsonObject.get("results");
    	  Log.v("TEST","going to assign json object to array");
    	  arr = (JSONArray)j;
    	  Log.v("TEST","length of array is " + arr.length());
    	} catch(Exception ex){
    	  Log.v("TEST","Exception: " + ex.getMessage());
    	}
    	
    	if (arr != null){
	    	Log.v(Main.TAG,"going to loop through array");
	    	for(int i = 0; i < arr.length(); i++) {
	    		   JSONObject t = (JSONObject) arr.get(i);
	    		   Tweet tweet = new Tweet(
	    		     t.get("from_user").toString(),
	    		     t.get("text").toString(),
	    		     t.get("profile_image_url").toString()
	    		   );
	    		   tweets.add(tweet);
	    		 }
    	}
    	return tweets;
    }
    
}
