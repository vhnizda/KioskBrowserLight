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

/**
 * Created by HnizdaV on 8/2/14.
 */

public class Kiosk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

        // Load the saved data link
        WebViewManager.Instance().setMyDataLink(this);

        // Get the current device display dimensions and load them
        Display display = getWindowManager().getDefaultDisplay();
        WebViewManager.Instance().setScreenDimensions(display.getWidth(),display.getHeight() + getStatusBarHeight()*13/7);

        // Add webview from this activity to the manager
        WebViewManager.Instance().setWebView1((WebView)findViewById(R.id.myBrowser1));

    }

    @Override
    protected void onResume(){
        super.onResume();

        WebViewManager.Instance().update(); //Web View Manager handles the details

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
            startActivity(intent);

            return true;
        }
        else if(id == R.id.kiosk_refresh)
        {
            WebViewManager.Instance().reload(); //Request the manager to reload the web views
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    //--  Custom Methods ----------------------------------------------------------------------//

    // Show or hide application bars
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

    // Figure out the dimensions of the Navigation Bar
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