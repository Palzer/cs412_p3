package edu.wwu.cs412.a3_palzerd;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

public class Main extends Activity  {
	public static final String TAG = "Mini Twitter";

    public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "in MainActivity onCreate");
        super.onCreate(savedInstanceState);
		FragmentManager.enableDebugLogging(true);
        setContentView(R.layout.main);
        //setOnClickListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.v(TAG, "in MainActivity onOptionsItemSelected. Selection = " + item );
    	switch (item.getItemId()) {
            case R.id.menu_settings:
                //newGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void send_search(String search_string)
    {
    	Log.v(TAG, "Search to send is: " + search_string);
    	((my_frag) getFragmentManager().findFragmentById(R.id.my_frag)).existing_search_string = search_string;
    	((my_frag) getFragmentManager().findFragmentById(R.id.my_frag)).do_search(search_string,1);
    }
    
    @Override
    public void onAttachFragment(Fragment fragment) {
		Log.v(TAG, "in MainActivity onAttachFragment. fragment id = "
				+ fragment.getId());
        super.onAttachFragment(fragment);
    }

    @Override
    public void onStart() {
		Log.v(TAG, "in MainActivity onStart");
    	super.onStart();
    }
    
    @Override
    public void onResume() {
		Log.v(TAG, "in MainActivity onResume");
    	super.onResume();
    }
    
    @Override
    public void onPause() {
		Log.v(TAG, "in MainActivity onPause");
    	super.onPause();
    }
    
    @Override
    public void onStop() {
		Log.v(TAG, "in MainActivity onStop");
    	super.onStop();
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	Log.v(Main.TAG, "in MainActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
		Log.v(TAG, "in MainActivity onDestroy");
    	super.onDestroy();
    }
    
}
