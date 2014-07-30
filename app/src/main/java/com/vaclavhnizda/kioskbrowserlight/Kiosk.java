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

public class Kiosk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code
        //setContentView(R.layout.activity_kiosk);

        //Hide all the status bars
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //Webview & Rotation
        WebView webView = new WebView(this);                    // Creates a new Webview
        webView.getSettings().setJavaScriptEnabled(true);       // Enables Javascript for webpages
        webView.setInitialScale(50);                            // Zooms out initially to 50%
//        webView.getSettings().setTextZoom(3);                   // Zooms text size down
        webView.setBackgroundColor(Color.BLACK);
        webView.loadUrl("http://ts.transitscreen.com/index.php/screen/index/296500");
        setContentView(webView);

//        int x = webView.getMeasuredWidth();
//        int y = webView.getMeasuredHeight();
//        webView.setMinimumHeight(x);
//        webView.setMinimumWidth(y);
        //webView.setRotation(-45);
        //webView.setFitsSystemWindows(true);

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
