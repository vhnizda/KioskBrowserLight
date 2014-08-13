package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HnizdaV on 8/10/14.
 */
public class Menu extends Activity{

    private static SharedPreferences preferences;
    private static final String KIOSK_FILE = "com.vaclavhnizda.kioskbrowserlight.save";
    private static final String URL_KEY = "com.vaclavhnizda.kioskbrowserlight.url";
    private static String url_address;
    private static final String WEB_ZOOM_KEY = "com.vaclavhnizda.kioskbrowserlight.webpage_zoom";
    private static int page_zoom_value;
    private static final String FONT_ZOOM_KEY = "com.vaclavhnizda.kioskbrowserlight.font_zoom";
    private static int font_zoom_value;
    private static final String ROTATION_KEY = "com.vaclavhnizda.kioskbrowserlight.rotation";
    private static int rotation_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_menu);

        //-- Load Saved Settings --------------------------------------------------------------//
        preferences = getSharedPreferences(KIOSK_FILE,Context.MODE_PRIVATE);

        TextView menu_url_location = (TextView)findViewById(R.id.menu_url_address);
        url_address = preferences.getString(URL_KEY,"failed to load URL");
        menu_url_location.setText(url_address);

        TextView menu_web_zoom = (TextView)findViewById(R.id.page_zoom_size);
        page_zoom_value = preferences.getInt(WEB_ZOOM_KEY,100);
        menu_web_zoom.setText(String.valueOf(page_zoom_value));

        TextView menu_font_zoom = (TextView)findViewById(R.id.font_zoom_size);
        font_zoom_value = preferences.getInt(FONT_ZOOM_KEY,100);
        menu_font_zoom.setText(String.valueOf(page_zoom_value));

        TextView menu_rotation = (TextView)findViewById(R.id.webpage_rotation);
        rotation_value = preferences.getInt(ROTATION_KEY,0);
        menu_rotation.setText(String.valueOf(rotation_value));


    }

    public void updateSettings(View view){
        // Get resources
        EditText temp = (EditText)findViewById(R.id.menu_url_address);
        url_address = temp.getText().toString();
        temp = (EditText)findViewById(R.id.page_zoom_size);
        page_zoom_value = Integer.parseInt(temp.getText().toString());
        temp = (EditText)findViewById(R.id.font_zoom_size);
        font_zoom_value = Integer.parseInt(temp.getText().toString());
        temp = (EditText)findViewById(R.id.webpage_rotation);
        rotation_value = Integer.parseInt(temp.getText().toString());

        // Save Values
        SharedPreferences.Editor myEditor = getSharedPreferences("kiosk_save",Context.MODE_PRIVATE).edit();
        myEditor.putString(URL_KEY,url_address);//add to save settings
        myEditor.putInt(WEB_ZOOM_KEY,page_zoom_value);
        myEditor.putInt(FONT_ZOOM_KEY,font_zoom_value);
        myEditor.putInt(ROTATION_KEY,rotation_value);
        myEditor.commit(); // commit changes
        goBack(view);
    }

    public void goBack(View view){
        finish();
    }

}
