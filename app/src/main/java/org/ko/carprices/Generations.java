package org.ko.carprices;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wrtg84 on 10/15/2015.
 */
public class Generations extends AsyncTask<String, Void,  Map<String, String>> {

    protected String url;

    protected Map<String, String> doInBackground(String... params) {
        String url = params[0];
        Map<String, String> generations = new HashMap<>();
        try {
            Connection.Response res = Jsoup.connect(url).userAgent("Mozilla").execute();
            Document doc = res.parse();
            Elements generationsElements = doc.getElementsByClass("sidebar-cat");
            int i=0;
            for (Element cat:generationsElements) {
                generations.put(cat.childNode(1).childNode(1).childNode(0).childNode(0).toString(), cat.childNode(1).attributes().get("href") );
            }
            //System.out.println(doc.toString());
        }
        catch(Exception e){
            e.toString();
            generations.clear();
        }
        return generations;
    }
}
