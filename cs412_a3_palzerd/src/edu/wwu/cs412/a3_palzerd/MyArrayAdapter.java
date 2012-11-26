package edu.wwu.cs412.a3_palzerd;

import java.io.IOException;
import java.net.MalformedURLException;
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
	
	class load_img extends AsyncTask<Object, Void, Bitmap> {
		
		private ImageView imageview;
		private String image_url;
		
		@Override
		protected Bitmap doInBackground(Object... params) {
			imageview = (ImageView) params[0];			
			image_url = imageview.getTag().toString();
			Bitmap bitmap = null;
			URL bitmap_url = null;
			try {
				bitmap_url = new URL(image_url);
			} catch (MalformedURLException e) {
				Log.v("Load","cannot get bitmap_url from image_url");
			}
		    try {
				return BitmapFactory.decodeStream(bitmap_url.openConnection().getInputStream());
			} catch (IOException e) {
				Log.v("Load","cannot get image from bitmap_url");
			}
			return bitmap; 
		}
		@Override
        protected void onPostExecute(Bitmap result) {
            if(result != null && imageview != null){
                imageview.setVisibility(View.VISIBLE);
                imageview.setImageBitmap(result);
            }else{
                imageview.setVisibility(View.GONE);
            }
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
			imageView.setTag(tweet.img_url);
			new load_img().execute(imageView);
		}
		return elem;
	}
}
