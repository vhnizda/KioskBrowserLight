package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HnizdaV on 8/10/14.
 */
public class Menu extends Activity{

    private static Data myDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        myDataStore = new Data(this);   // Data save class

        getActionBar().hide();

        //-- Load Settings -----------------------------------------------------------------------//
        TextView menu_url_location = (TextView)findViewById(R.id.menu_url_address);
        menu_url_location.setText(myDataStore.getUrl_address());
        TextView menu_web_zoom = (TextView)findViewById(R.id.page_zoom_size);
        menu_web_zoom.setText(String.valueOf(myDataStore.getWeb_zoom_value()));
        TextView menu_font_zoom = (TextView)findViewById(R.id.font_zoom_size);
        menu_font_zoom.setText(String.valueOf(myDataStore.getFont_zoom_value()));
        TextView menu_rotation = (TextView)findViewById(R.id.webpage_rotation);
        menu_rotation.setText(String.valueOf(myDataStore.getRotation_value()));
    }

    public void updateSettings(View view){
        //-- Update Settings ---------------------------------------------------------------------//
        EditText temp = (EditText)findViewById(R.id.menu_url_address);
        myDataStore.setUrl_address(temp.getText().toString());

        temp = (EditText)findViewById(R.id.page_zoom_size);
        myDataStore.setWeb_zoom_value(Integer.parseInt(temp.getText().toString()));

        temp = (EditText)findViewById(R.id.font_zoom_size);
        myDataStore.setFont_zoom_value(Integer.parseInt(temp.getText().toString()));

        temp = (EditText)findViewById(R.id.webpage_rotation);
        myDataStore.setRotation_value(Integer.parseInt(temp.getText().toString()));

        goBack(view);   // Return to Kiosk Browser
    }

    public void goBack(View view){  // This option is used by updateSettings() and the Cancel button.
        finish();
    }

}