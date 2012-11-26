package edu.wwu.cs412.a3_palzerd;

import java.net.URL;
import java.util.List;

import org.json.JSONException;

import edu.wwu.cs412.a3_palzerd.my_frag.Tweet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Tweet>{
	
	private final Context context;
	private final List<Tweet> tweets;
	
	private class download_img extends AsyncTask<String,Void,Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
			    URL url = new URL(params[0]);
			    return BitmapFactory.decodeStream(url.openConnection().getInputStream());
			  }
			  catch(Exception ex) {
				  Log.v("Hey","the exeption is: " + ex);
				  return null;}
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
		}
	}
	

	
	public MyArrayAdapter(Context context, List<Tweet> tweets){
		super(context, R.layout.twitlist, tweets);
		this.context = context;
		this.tweets = tweets;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View elem = inflater.inflate(R.layout.twitlist, parent, false);
		TextView userView = (TextView) elem.findViewById(R.id.twit_username);
		TextView msgView = (TextView) elem.findViewById(R.id.twit_message);
		ImageView imageView = (ImageView) elem.findViewById(R.id.twit_img);
		
		Tweet tweet = tweets.get(position);
		if (tweet != null){
			userView.setText(tweet.username);
			msgView.setText(tweet.message);
			download_img task = new download_img();
			
	    	task.execute(new String[] {tweet.img_url});
		}
		return elem;
	}
}
