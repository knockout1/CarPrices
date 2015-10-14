package org.ko.carprices;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by wrtg84 on 10/13/2015.
 */
public class Categories extends AsyncTask<Void, Void,  ArrayList<String>> {

    protected String url;
    protected String categories;
    protected ArrayList<String> doInBackground(Void... params) {
        String url = "http://allegro.pl/samochody-osobowe-4029";
        //String categories[] = new String[100];
        ArrayList<String> categories = new ArrayList<String>();
        try {
            Connection.Response res = Jsoup.connect(url).userAgent("Mozilla").execute();
            Document doc = res.parse();
            Elements categoriesElements = doc.getElementsByClass("sidebar-cat");
            int i=0;
            for (Element cat:categoriesElements) {
                categories.add(cat.childNode(1).childNode(1).childNode(0).childNode(0).toString());
            }
            //System.out.println(doc.toString());
        }
        catch(Exception e){
            e.toString();
        }
        return categories;
    }
}
