package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.view.View;
import android.app.ActionBar;
import android.webkit.WebViewClient;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import android.view.Display;
import android.view.ViewGroup;

public class Kiosk extends Activity {

    private WebView xmlWebView = null;

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        xmlWebView.saveState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code - load layout
        setContentView(R.layout.activity_kiosk);

        // Get the current display dimensions
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        // Change layout of outer frame to oversize square to prevent cropping
        RelativeLayout myRelLayout = (RelativeLayout)findViewById(R.id.main);
        LayoutParams layoutParams = myRelLayout.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;

        // Get the webview
//        xmlWebView = new WebView(this);
        xmlWebView = (WebView)findViewById(R.id.myBrowser);




        // Change settings on webview
        if(savedInstanceState != null)
        {
            xmlWebView.restoreState(savedInstanceState);
        }
        else
        {
            // turn off hardware acceleration to allow the rotation to work!!
            // http://stackoverflow.com/questions/18684172/webview-setrotation-creates-a-blank-page
            xmlWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            xmlWebView.setRotation(90.0f);
            // Enable Javascript
            xmlWebView.getSettings().setJavaScriptEnabled(true);

            xmlWebView.getSettings().setBuiltInZoomControls(true);
            xmlWebView.loadUrl("http://ts.transitscreen.com/index.php/screen/index/296500");  // 1776 TS sign
//            xmlWebView.loadUrl("http://transitscreenstaging.herokuapp.com/index.php/screen/index/296500");  //heroku test
//            xmlWebView.loadUrl("http://secretdesignproject.com/demo/ts/3/index.php/screen/index/941065");  // white version of TS
//            xmlWebView.loadUrl("http://www.google.com"); // google test
//            xmlWebView.measure(height,width);


//            RelativeLayout.LayoutParams myLayout = new RelativeLayout.LayoutParams(height,width);
            ViewGroup.LayoutParams myLayout = xmlWebView.getLayoutParams();
            ViewParent test = xmlWebView.getParent();
            myLayout.height = width;
            myLayout.width = height;
            xmlWebView.setX(0);
            xmlWebView.setY(0);
            xmlWebView.setInitialScale(80);

//            xmlWebView.setLayoutParams(myLayout);
//            xmlWebView.setFitsSystemWindows(true);

            // Override loading of a new browser, open internally only!
            // http://stackoverflow.com/questions/7308904/link-should-be-open-in-same-web-view-in-android
            xmlWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });
        }


        //Hide all the status bars
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //Moves the webview to center of screen: compensates for oversize parent layout
        float temp1 = (width-height)/2;
        xmlWebView.setTranslationY(-temp1);

//        // Test existing XML data
//        WebView xmlWebView = (WebView)findViewById(R.id.testWebpageView);
//        xmlWebView.loadUrl("www.google.com");
//        xmlWebView.setRotation(20);


//        //Attempt to rotate the screen - http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
//        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.main);
//        int w = mainLayout.getWidth();
//        int h = mainLayout.getHeight();

//        mainLayout.setRotation(45.0f);
//        mainLayout.setTranslationX((w - h) / 2);
//        mainLayout.setTranslationY((h - w) / 2);
//        mainLayout.setRotation(270.0f);
//        mainLayout.setTranslationX(20);
//        mainLayout.setTranslationY(20);
//
//        xmlWebView.loadUrl("http://www.google.com");

//        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mainLayout.getLayoutParams();
//        lp.height = w;
//        lp.width = h;
//        mainLayout.invalidate();

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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
