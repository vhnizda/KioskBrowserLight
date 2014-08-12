package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.content.Context;
import android.view.View;

/**
 * Created by HnizdaV on 8/10/14.
 */
public class Menu extends Activity{

    private static final String URL_KEY = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_menu);


    }

    public void UpdateSettings(View view){
        EditText temp = (EditText)findViewById(R.id.url_address);
        String myURL = temp.getText().toString();
        SharedPreferences.Editor myEditor = getPreferences(Context.MODE_PRIVATE).edit();
        myEditor.putString(URL_KEY,myURL);//add to save settings
//        Kiosk.SetURL(myURL); //add to view
    }

}
