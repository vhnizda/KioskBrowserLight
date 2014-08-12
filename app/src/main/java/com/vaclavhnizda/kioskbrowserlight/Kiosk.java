package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.view.View;
import android.app.ActionBar;
import android.content.Intent;
import android.view.ViewGroup.LayoutParams;
import android.view.Display;
import android.view.ViewGroup;
//import android.graphics.Point;

public class Kiosk extends Activity {

    private static WebView xmlWebView = null;
    private static SharedPreferences preferences;
    private static final String URL_KEY = "url";
    private static String url_Address = null;
    private static final String ZOOM_KEY = "webpage_zoom";
    private static int zoom_Value = 70;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_kiosk);

        //-- Load Saved Settings --------------------------------------------------------------//
        preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_Prefs = preferences.edit();

        preferences.getString(URL_KEY,url_Address);
        if(url_Address == null)
        {
            url_Address = "http://www.google.com";
            edit_Prefs.putString(URL_KEY,url_Address);
            edit_Prefs.commit();
        }



        //-- Setup Layout Settings ------------------------------------------------------------//

        // Get the current device display dimensions
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
//        Point myPoints = new Point(); //Alternate code to display.getWidth()??
//        display.getSize(myPoints);
//        int width = myPoints.;

        // Change layout of outer frame to oversize square to prevent cropping of children
        RelativeLayout myRelLayout = (RelativeLayout)findViewById(R.id.main);
        LayoutParams layoutParams = myRelLayout.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;

        // Get webview #1
        xmlWebView = (WebView)findViewById(R.id.myBrowser1);

        // Webpage Initial settings
        xmlWebView.getSettings().setJavaScriptEnabled(true);    // Enable Javascript
        xmlWebView.loadUrl(url_Address);              // Load Webpage
        xmlWebView.setInitialScale(70);     //Set Scale - smaller for smaller screens!
//        xmlWebView.getSettings().setDefaultFontSize(30);  //Set font size


        // Webpage Rotation Changes
        xmlWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);// Disable hardware acceleration to allow rotation
        xmlWebView.setRotation(90.0f);                          // Rotate Webpage


        //Adjust screen if rotated sideways
        if(xmlWebView.getRotation() == 90 || xmlWebView.getRotation() == 270) {
            float temp1 = (width - height) / 2;
            xmlWebView.setTranslationY(-temp1);
            xmlWebView.setTranslationX(temp1);

            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams(); // Extract Layout
            myLayout.height = width;            // Flip dimension
            myLayout.width = height;            // Flip dimension

        }

//        //Hide all the status bars
//        View decorView = getWindow().getDecorView();
//        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
//        // Remember that you should never show the action bar if the
//        // status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

    }

    @Override
    protected void onResume(){
        super.onResume();
        preferences.getString(URL_KEY,url_Address);
        xmlWebView.loadUrl(url_Address);
    }


    //----- Menu Settings ---------------------------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kiosk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.kiosk_settings) {

            Intent intent = new Intent(this, com.vaclavhnizda.kioskbrowserlight.Menu.class);
//            EditText editText = (EditText) findViewById(R.id.edit_message);
//            String message = editText.getText().toString();
//            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        preferences.getString(URL_KEY,url_Address);
//        xmlWebView.loadUrl(url_Address);
//    }

//    public static void SetURL(String url_String)
//    {
//        if(xmlWebView != null) {
//            xmlWebView.loadUrl(url_String);
//        }
//    }
}

// Sources of inspiration
// http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
// http://stackoverflow.com/questions/18684172/webview-setrotation-creates-a-blank-page