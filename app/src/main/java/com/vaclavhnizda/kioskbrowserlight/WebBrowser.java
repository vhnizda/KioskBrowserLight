package com.vaclavhnizda.kioskbrowserlight;

import android.webkit.WebViewClient;
import android.webkit.WebView;

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
