package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HnizdaV on 8/10/14.
 */
public class Menu extends Activity{

    private static SharedPreferences preferences;
    private static final String URL_KEY = "url";
    private static String url_Address = null;
    private static final String ZOOM_KEY = "webpage_zoom";
    private static int zoom_Value = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_menu);

        //-- Load Saved Settings --------------------------------------------------------------//
        preferences = getSharedPreferences("kiosk_save",Context.MODE_PRIVATE);

        TextView menu_url_location = (TextView)findViewById(R.id.menu_url_address);

        url_Address = preferences.getString(URL_KEY,null);
        if(url_Address == null)
        {
            url_Address = "failed to load URL";
        }
        menu_url_location.setText(url_Address);

    }

    public void UpdateSettings(View view){
        EditText temp = (EditText)findViewById(R.id.menu_url_address);
        String myURL = temp.getText().toString();

        SharedPreferences.Editor myEditor = getSharedPreferences("kiosk_save",Context.MODE_PRIVATE).edit();

        myEditor.putString(URL_KEY,myURL);//add to save settings
        myEditor.commit(); // commit changes
    }

}
