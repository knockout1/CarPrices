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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_details);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        setTitle(intent.getStringExtra("name"));
        try {
            prices = new Prices().execute(url).get();
        }
        catch (Exception e){

        }
        float sum = 0;
        for(Object price:prices)
        {
            sum+=Float.parseFloat(price.toString());
        }

        Collections.sort(prices);

        averagerPriceTextView = (TextView)findViewById(R.id.averagepriceTextView);
        minPriceTextView = (TextView)findViewById(R.id.averagepriceTextView);
        maxPriceTextView = (TextView)findViewById(R.id.averagepriceTextView);


        averagerPriceTextView.setText("Average Price = "+Float.toString(sum/prices.size()));
        minPriceTextView.setText("Average Price = "+prices.get(0));
        maxPriceTextView.setText("Average Price = "+prices.get(prices.size() - 1));
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
