package org.ko.carprices;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by wrtg84 on 10/24/2015.
 */
public class PagesNumber extends AsyncTask<String, Void,  Integer> {

        protected String url;

        protected Integer doInBackground(String... params) {
                String url = params[0];
                int i =0;
                try{
                Connection.Response res = Jsoup.connect(url).userAgent("Mozilla").execute();
                Document doc = res.parse();
                Elements pages = doc.getElementsByClass("last");
                        i = Integer.parseInt(pages.get(1).childNode(0).toString());
                }
                catch (Exception e){

                }
        return i;
        }
        }