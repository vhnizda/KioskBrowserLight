package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.view.View;
import android.app.ActionBar;
import android.view.ViewGroup.LayoutParams;
import android.view.Display;
import android.view.ViewGroup;
//import android.graphics.Point;

public class Kiosk extends Activity {

    private WebView xmlWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_kiosk);

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
        xmlWebView.loadUrl(WebPageList.GetUrl(1));              // Load Webpage
        xmlWebView.setInitialScale(80);     //Set Scale - smaller for smaller screens!


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

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

// Sources of inspiration
// http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
// http://stackoverflow.com/questions/18684172/webview-setrotation-creates-a-blank-page