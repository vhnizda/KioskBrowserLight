package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.view.View;
import android.app.ActionBar;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.view.Display;
import android.graphics.Point;
import android.widget.RelativeLayout;
import android.view.ViewGroup;

public class Kiosk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code
        setContentView(R.layout.activity_kiosk);

        //Hide all the status bars
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();


//        // Test existing XML data
//        WebView xmlWebView = (WebView)findViewById(R.id.testWebpageView);
//        xmlWebView.loadUrl("www.google.com");
//        xmlWebView.setRotation(20);


//        //Attempt to rotate the screen - http://stackoverflow.com/questions/21355784/android-rotate-whole-layout
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main);
        int w = mainLayout.getWidth();
        int h = mainLayout.getHeight();

//        mainLayout.setRotation(270.0f);
        mainLayout.setTranslationX((w - h) / 2);
        mainLayout.setTranslationY((h - w) / 2);
//
//        WebView xmlWebView = new WebView(this);
//        xmlWebView.loadUrl("http://www.google.com");
//        mainLayout.addView(xmlWebView);
//
//        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mainLayout.getLayoutParams();
//        lp.height = w;
//        lp.width = h;
        mainLayout.requestLayout();




//        // Gets the dimensions of the screen.
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);




        // One idea of creating everything here..

//        FrameLayout myFrame = new FrameLayout(this);
//        myFrame.setBackgroundColor(Color.BLACK);
//        int oldBottom = myFrame.getBottom();
//        //myFrame.setRotation(90);
//
//
//        //Webview & Rotation
//        WebView webView = new WebView(this);                    // Creates a new Webview
//        webView.getSettings().setJavaScriptEnabled(true);       // Enables Javascript for webpages
//        webView.setInitialScale(60);                            // Zooms out initially to 50% for smaller screens
//        webView.setRotation(90);
////        webView.getSettings().setTextZoom(3);                   // Zooms text size down
//        //webView.setBackgroundColor(Color.BLACK);
//        webView.loadUrl("http://ts.transitscreen.com/index.php/screen/index/111");
////        webView.loadUrl("http://ts.transitscreen.com/index.php/screen/index/296500");
//        //webView.loadUrl("http://knowledgeboxes.bitbucket.org");
//
////        setContentView(webView);                                // How webpage get's pushed to device screen
//
//        //setContentView(webView);
//        myFrame.addView(webView);
//        setContentView(myFrame);

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
