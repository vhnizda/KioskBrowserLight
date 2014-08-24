package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.view.View;
import android.app.ActionBar;
import android.content.Intent;
import android.view.Display;
import android.view.ViewGroup;

public class Kiosk extends Activity {

    private static WebView xmlWebView;
    private static Data myData;
    private int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_kiosk);

        //-- Load Saved Settings --------------------------------------------------------------//
        myData = new Data(this);   // Data save class

        //-- Setup Layout Settings ------------------------------------------------------------//

        // Get the current device display dimensions
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight() + getStatusBarHeight();//adding status bar height because it's ignored on Riko stick;

        // Prepare webview #1
        xmlWebView = (WebView)findViewById(R.id.myBrowser1);
        xmlWebView.setWebViewClient(new WebBrowser()); // Run hyperlinks internally only
        xmlWebView.getSettings().setJavaScriptEnabled(true);    // Enable Javascript

    }

    @Override
    protected void onResume(){
        super.onResume();

        // Setup Webview
        xmlWebView.setInitialScale(myData.getWeb_zoom_value());
        xmlWebView.getSettings().setTextZoom(myData.getFont_zoom_value());
        xmlWebView.setRotation(new Float(myData.getRotation_value()));                          // Rotate Webpage
        xmlWebView.loadUrl(myData.getUrl_address());




        //Adjust screen based on rotation
        if(xmlWebView.getRotation() == 90 || xmlWebView.getRotation() == 270) {

            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams(); // Extract Layout
            myLayout.height = width;            // Flip dimension
            myLayout.width = height + getStatusBarHeight()*6/7;//adding status bar height because it's ignored on Riko stick

        }
        else{

            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams(); // Extract Layout
            myLayout.height = height + getStatusBarHeight()*6/7;            // Flip dimension
            myLayout.width = width;            // Flip dimension
        }

        toggleActionBar(this.getCurrentFocus()); // Switch to fullscreen after successful load


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
        else if(id == R.id.kiosk_refresh)
        {
            xmlWebView.reload();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop(){
        super.onStop();

//        xmlWebView.clearCache(true);
    }

    //--  Custom Methods ----------------------------------------------------------------------//
    public void toggleActionBar(View view){
        // Hide the Action bar -> with menu button
        ActionBar actionBar = getActionBar();
        if(actionBar.isShowing()){
            actionBar.hide();
            this.getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
        else{
            actionBar.show();

        }
    }

    // Added to dimensions to compensate for Navigation Bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}



// Sources of reference
// http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
// http://stackoverflow.com/questions/18684172/webview-setrotation-creates-a-blank-page
// http://www.tutorialspoint.com/android/android_webview_layout.htm
// http://stackoverflow.com/questions/10445157/easy-way-to-hide-system-bar-on-android-ics
// http://mrtn.me/blog/2012/03/17/get-the-height-of-the-status-bar-in-android/