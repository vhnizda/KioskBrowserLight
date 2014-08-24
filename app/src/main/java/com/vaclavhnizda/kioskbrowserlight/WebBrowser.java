package com.vaclavhnizda.kioskbrowserlight;

import android.webkit.WebViewClient;
import android.webkit.WebView;

/**
 * Created by HnizdaV on 8/12/14.
 * Used to customize how the WebView widget loads links when clicked on inside of webview.
 * This prevents them from loading as an Intent in the default browser app.
 */
public class WebBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
