package com.vaclavhnizda.kioskbrowserlight;

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by HnizdaV on 8/24/14.
 * This class is used to manage changes in the webviews
 */
public class WebViewManager {

    // Only one instance of this class allowed.
    private static WebViewManager singleton;
    private WebViewManager()
    {

    }

    public static WebViewManager Instance(){
        if(singleton == null){singleton = new WebViewManager();}
        return singleton;
    }

    // Local Variables
    private WebView myBrowser1;  //Webview link
    private Data myData; // data access
    private int width,height; // holds screen height/width


    //-- Update web views here ------------------------------------------------------------------//
    public void update(){

        if(myData == null || width <= 0 || height <= 0 || myBrowser1 == null){
            return;
        }

        // Setup Webview
        myBrowser1.setInitialScale(myData.getWeb_zoom_value());
        myBrowser1.getSettings().setTextZoom(myData.getFont_zoom_value());
        myBrowser1.setRotation(new Float(myData.getRotation_value()));     // Rotate Webpage
        myBrowser1.loadUrl(myData.getUrl_address());

        //Adjust screen based on rotation
        if(myBrowser1.getRotation() == 90 || myBrowser1.getRotation() == 270) {

            ViewGroup.LayoutParams myLayout = myBrowser1.getLayoutParams(); // Extract Layout
            myLayout.height = width;            // Flip dimension
            myLayout.width = height;

        }
        else{

            ViewGroup.LayoutParams myLayout = myBrowser1.getLayoutParams(); // Extract Layout
            myLayout.height = height;           // Flip dimension
            myLayout.width = width;            // Flip dimension
        }

    }

    // Refresh screen
    public void reload(){
        myBrowser1.reload();
    }

    //-- Setters for local variables ------------------------------------------------------------//
    public void setWebView1(WebView newView){
        myBrowser1 = newView;
        myBrowser1.setWebViewClient(new WebBrowser()); // Run hyperlinks internally only
        myBrowser1.getSettings().setJavaScriptEnabled(true);    // Enable Javascript
    }

    public void setScreenDimensions(int newWidth, int newHeight){
        width = newWidth;
        height = newHeight;
    }

    // This is where an activity passes itself as the context link
    public void setMyDataLink(Context myContext){
        myData = new Data(myContext);
    }
}
