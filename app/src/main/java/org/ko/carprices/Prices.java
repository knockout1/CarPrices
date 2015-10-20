package org.ko.carprices;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wrtg84 on 10/15/2015.
 */
public class Prices extends AsyncTask<String, Void,  ArrayList> {

    protected String url;

    protected ArrayList doInBackground(String... params) {
        String url = params[0];
        ArrayList prices = new ArrayList();

        try{
            Connection.Response res = Jsoup.connect(url).userAgent("Mozilla").execute();
            Document doc = res.parse();
            Elements carPrices = doc.getElementsByClass("offer-price");
            int i = 0;
            for (Element cat : carPrices) {
                String stringPrice = cat.childNode(1).childNode(1).childNode(0).toString();
                stringPrice = stringPrice.replace("&nbsp;","");
                stringPrice = stringPrice.replace(",",".");
                stringPrice = stringPrice.substring(0, stringPrice.length()-3);
                prices.add(Float.parseFloat(stringPrice));

            }
        }
        catch (Exception e){

        }
        return prices;
    }
}