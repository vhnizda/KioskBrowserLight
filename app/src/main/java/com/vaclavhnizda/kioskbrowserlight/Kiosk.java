package com.vaclavhnizda.kioskbrowserlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Kiosk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Original code
        //setContentView(R.layout.activity_kiosk);

        //4 Webview Rotation test
        WebView webView = new WebView(this);
//        int x = webView.getMeasuredWidth();
//        int y = webView.getMeasuredHeight();
//        webView.setMinimumHeight(x);
//        webView.setMinimumWidth(y);
        //webView.setRotation(90);
        webView.setFitsSystemWindows(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://ts.transitscreen.com/index.php/screen/index/296500");
        setContentView(webView);

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
