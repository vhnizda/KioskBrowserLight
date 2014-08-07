package com.vaclavhnizda.kioskbrowserlight;

/**
 * Created by HnizdaV on 8/7/14.
 */
public class WebPageList {
    private static String[] urls = new String[4];

    private static WebPageList singleton;

    private WebPageList()
    {
        urls[0] = "http://ts.transitscreen.com/index.php/screen/index/296500";  // 1776 TS sign
        urls[1] = "http://transitscreenstaging.herokuapp.com/index.php/screen/index/296500";  //heroku test
        urls[2] = "http://secretdesignproject.com/demo/ts/3/index.php/screen/index/941065";  // white version of TS
        urls[3] = "http://www.google.com"; // google test
    }

    public static String GetUrl(int number)
    {
        CheckSingleton();

        if(1 <= number && number <= 4) {
            return urls[number - 1];
        }
        else
            return urls[3];
    }

    private static void CheckSingleton()
    {
        if (singleton == null)
        {
            singleton = new WebPageList();
        }
    }

}
