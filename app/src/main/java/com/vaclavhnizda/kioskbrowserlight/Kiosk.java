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
import android.view.MotionEvent;
import android.webkit.WebViewClient;
//import android.graphics.Point;

public class Kiosk extends Activity {

    private static WebView xmlWebView;
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
        setContentView(R.layout.activity_kiosk);

        //-- Load Saved Settings --------------------------------------------------------------//
        preferences = getSharedPreferences(KIOSK_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_Prefs = preferences.edit();

        url_address = preferences.getString(URL_KEY,null);
        if(url_address == null)
        {
            url_address = "http://www.google.com";
            edit_Prefs.putString(URL_KEY,url_address);
            edit_Prefs.commit();
        }

        page_zoom_value = preferences.getInt(WEB_ZOOM_KEY, 0);
        if(page_zoom_value == 0)
        {
            page_zoom_value = 100;
            edit_Prefs.putInt(WEB_ZOOM_KEY,page_zoom_value);
            edit_Prefs.commit();

        }

        font_zoom_value = preferences.getInt(FONT_ZOOM_KEY, 0);  // setTextZoom(int)
        if(font_zoom_value == 0)
        {
            font_zoom_value = 100;
            edit_Prefs.putInt(FONT_ZOOM_KEY,font_zoom_value);
            edit_Prefs.commit();
        }

        rotation_value = preferences.getInt(ROTATION_KEY, -1);
        if(rotation_value == -1)
        {
            rotation_value = 0;
            edit_Prefs.putInt(ROTATION_KEY, rotation_value);
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
//        xmlWebView = new WebView(this);
//        xmlWebView.setWebViewClient(new WebBrowser()); //embed custom browser which opens links internally
//        xmlWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggleActionBar(view);
//            }
//        });

        // Webpage Initial settings
        xmlWebView.getSettings().setJavaScriptEnabled(true);    // Enable Javascript
        xmlWebView.loadUrl(url_address);              // Load Webpage
        xmlWebView.setInitialScale(page_zoom_value);     //Set Scale - smaller for smaller screens!
//        xmlWebView.getSettings().setDefaultFontSize(30);  //Set font size


        // Webpage Rotation Changes
        xmlWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);// Disable hardware acceleration to allow rotation
        xmlWebView.setRotation(rotation_value);                          // Rotate Webpage


        //Adjust screen if rotated sideways
        if(xmlWebView.getRotation() == 90 || xmlWebView.getRotation() == 270) {
            float temp1 = (width - height) / 2;
            xmlWebView.setTranslationY(-temp1);
            xmlWebView.setTranslationX(temp1);

            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams(); // Extract Layout
            myLayout.height = width;            // Flip dimension
            myLayout.width = height;            // Flip dimension

        }
        else
        {
            xmlWebView.setTranslationY(0);
            xmlWebView.setTranslationX(0);

            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams(); // Extract Layout
            myLayout.height = height;            // Flip dimension
            myLayout.width = width;            // Flip dimension
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        url_address = preferences.getString(URL_KEY,"http://www.google.com");
        xmlWebView.loadUrl(url_address);
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

    @Override
    protected void onStop(){
        super.onStop();

        xmlWebView.clearCache(true);
    }

    //--  Custom Methods ----------------------------------------------------------------------//
    public void toggleActionBar(View view){
        // Hide the Action bar -> with menu button
        ActionBar actionBar = getActionBar();
        if(actionBar.isShowing()){
            actionBar.hide();
        }
        else{
            actionBar.show();
        }

        //--  Hide all the status bars  ---------------------------------------------------------//
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
//        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
    }
}



// Sources of inspiration
// http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
// http://stackoverflow.com/questions/18684172/webview-setrotation-creates-a-blank-page
// http://www.tutorialspoint.com/android/android_webview_layout.htm