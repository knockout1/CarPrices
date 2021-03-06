package org.ko.carprices;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wrtg84 on 10/13/2015.
 */
public class Categories extends AsyncTask<String, Void,  Map<String, String>> {

    protected String url;
    protected String categories;
    protected Map<String, String> doInBackground(String... params) {
        String url = params[0];
        //ArrayList<String> categories = new ArrayList<String>();
        Map<String, String> categories = new HashMap<>();
        try {
            Connection.Response res = Jsoup.connect(url).userAgent("Mozilla").execute();
            Document doc = res.parse();
            Elements categoriesElements = doc.getElementsByClass("sidebar-cat");
            int i=0;
            for (Element cat:categoriesElements) {
                categories.put(cat.childNode(1).childNode(1).childNode(0).childNode(0).toString(), cat.childNode(1).attributes().get("href") );
            }
            //System.out.println(doc.toString());
        }
        catch(Exception e){
            e.toString();
        }
        return categories;
    }
}
