package org.ko.carprices;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class PriceDetailsActivity extends ActionBarActivity {
    protected String url;
    protected ArrayList prices = new ArrayList();
    protected TextView averagerPriceTextView;
    protected TextView minPriceTextView;
    protected TextView maxPriceTextView;
    protected TextView numberOfOffers;
    protected TextView mediana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_details);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("name"));
        url = intent.getStringExtra("url");
        try {
            int sitesNumber = new PagesNumber().execute(url).get();
            for (int i=1; i<=sitesNumber; i++) {
                String urlPaged = url + "?p="+Integer.toString(i);
                prices.addAll(new Prices().execute(urlPaged).get());
            }
        }
        catch (Exception e){

        }
        float sum = 0;
        for(Object price:prices)
        {
            sum+=Float.parseFloat(price.toString());
        }

        Collections.sort(prices);
/*
        int middle = prices.size()/2;
        float med = 0;
        if (prices.size()%2 == 1) {
            med  = Float.parseFloat(prices.get(middle).toString());
        } else {
            return (Float.parseFloat(prices.get(middle-1).toString()) + Float.parseFloat(prices.get(middle).toString())) / 2.0;
        }
  */
        averagerPriceTextView = (TextView)findViewById(R.id.averagepriceTextView);
        minPriceTextView = (TextView)findViewById(R.id.minPriceTextView);
        maxPriceTextView = (TextView)findViewById(R.id.maxPriceTextView);
        numberOfOffers = (TextView)findViewById(R.id.numberOfOffers);
        mediana = (TextView)findViewById(R.id.mediana);

        averagerPriceTextView.setText("Average Price = "+Float.toString(sum/prices.size()));
        minPriceTextView.setText("Minimal Price = "+prices.get(0));
        maxPriceTextView.setText("Maximal Price = "+prices.get(prices.size() - 1));
        numberOfOffers.setText("Number of offers ="+prices.size());
        mediana.setText("Mediana = ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_price_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
