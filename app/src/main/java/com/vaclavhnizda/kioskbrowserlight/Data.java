package com.vaclavhnizda.kioskbrowserlight;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HnizdaV on 8/20/14.
 */
public class Data {

    //--Private variables ------------------------------------------------------------------------//
    private static SharedPreferences preferences;
    SharedPreferences.Editor         myEditor;
    private static final String      KIOSK_FILE      = "com.vaclavhnizda.kioskbrowserlight.save";
    private static final String      URL_KEY         = "com.vaclavhnizda.kioskbrowserlight.url";
    private static final String      WEB_ZOOM_KEY    = "com.vaclavhnizda.kioskbrowserlight.webpage_zoom";
    private static final String      FONT_ZOOM_KEY   = "com.vaclavhnizda.kioskbrowserlight.font_zoom";
    private static final String      ROTATION_KEY    = "com.vaclavhnizda.kioskbrowserlight.rotation";
    private static String   url_address;
    private static int      web_zoom_value;
    private static int      font_zoom_value;
    private static int      rotation_value;

    //--Constructor ------------------------------------------------------------------------------//

    public Data(Context myContext)
    {
        //-- Load Saved Settings -----------------------------------------------------------------//
        preferences = myContext.getSharedPreferences(KIOSK_FILE, Context.MODE_PRIVATE);
        myEditor = preferences.edit();

        url_address = preferences.getString(URL_KEY,null); // Check if not set.
        if(url_address == null)
        {
            url_address = "http://www.google.com";
            myEditor.putString(URL_KEY,url_address); //Save default to data store
            myEditor.commit();
        }

        web_zoom_value = preferences.getInt(WEB_ZOOM_KEY, -1); // Check if not set.
        if(web_zoom_value == -1)
        {
            web_zoom_value = 100;
            myEditor.putInt(WEB_ZOOM_KEY,web_zoom_value); //Save default to data store
            myEditor.commit();
        }

        font_zoom_value = preferences.getInt(FONT_ZOOM_KEY, -1);  // Check if not set.
        if(font_zoom_value == -1)
        {
            font_zoom_value = 100;
            myEditor.putInt(FONT_ZOOM_KEY,font_zoom_value); //Save default to data store
            myEditor.commit();
        }

        rotation_value = preferences.getInt(ROTATION_KEY, -1);
        if(rotation_value == -1)
        {
            rotation_value = 0;
            myEditor.putInt(ROTATION_KEY, rotation_value); //Save default to data store
            myEditor.commit();
        }
    }

    //-- Retrieve Values -------------------------------------------------------------------------//
    public int getRotation_value() {
        return rotation_value;
    }

    public int getFont_zoom_value() {
        return font_zoom_value;
    }

    public int getWeb_zoom_value() {
        return web_zoom_value;
    }

    public String getUrl_address() {
        return url_address;
    }

    //-- Save Values -----------------------------------------------------------------------------//

    public void setUrl_address(String url_address) {
        Data.url_address = url_address;
        myEditor.putString(URL_KEY,url_address);
        myEditor.commit();
    }

    public void setWeb_zoom_value(int web_zoom_value) {
        Data.web_zoom_value = web_zoom_value;
        myEditor.putInt(WEB_ZOOM_KEY,web_zoom_value);
        myEditor.commit();
    }

    public void setFont_zoom_value(int font_zoom_value) {
        Data.font_zoom_value = font_zoom_value;
        myEditor.putInt(FONT_ZOOM_KEY,font_zoom_value);
        myEditor.commit();
    }

    public void setRotation_value(int rotation_value) {
        Data.rotation_value = rotation_value;
        myEditor.putInt(ROTATION_KEY,rotation_value);
        myEditor.commit();
    }
}