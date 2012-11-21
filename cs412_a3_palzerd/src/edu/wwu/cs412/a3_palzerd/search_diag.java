package edu.wwu.cs412.a3_palzerd;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;

import android.widget.TextView;

public class search_diag extends DialogFragment implements OnEditorActionListener{
	
	private EditText mEditText;

    public search_diag() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diag_ui, container);
        mEditText = (EditText) view.findViewById(R.id.search_edittext); 
        
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);

        
        return view;
    }

	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		Log.v(Main.TAG,"Action is " + actionId);
		if (EditorInfo.IME_ACTION_DONE == actionId)
		{
			Log.v(Main.TAG,"done editting search terms");
		}
		return false;
	}
}
