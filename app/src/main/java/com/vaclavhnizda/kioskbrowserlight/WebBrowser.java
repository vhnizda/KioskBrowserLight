package com.vaclavhnizda.kioskbrowserlight;

import android.webkit.WebViewClient;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HnizdaV on 8/12/14.
 */
public class WebBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
