package org.ko.carprices;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CarGenerationsActivity extends ActionBarActivity {
    ListView carGenerationsListView;
    Map<String, String> generationsMap = new HashMap<String, String>();
    ArrayAdapter<String> adapter;
    String[] params;
    String url = new String();
    String name = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_generations);
        try {
            Intent intent = getIntent();
            url = intent.getStringExtra("url");
            name = intent.getStringExtra("name");
            setTitle(name);
            generationsMap = new Generations().execute(url).get();
        }
        catch(Exception e) {

        }
        if(generationsMap.size() > 0) {
            List<String> generationsList = keysAsList(generationsMap);
            carGenerationsListView = (ListView) findViewById(R.id.carGenerationsListView);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, generationsList);
            carGenerationsListView.setAdapter(adapter);

            carGenerationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemValue = (String) carGenerationsListView.getItemAtPosition(position);
                    url = "http://allegro.pl" + generationsMap.get(itemValue).toString();

                    Intent startPriceDetailsActivityIntent = new Intent(CarGenerationsActivity.this, PriceDetailsActivity.class);
                    startPriceDetailsActivityIntent.putExtra("url", url);

                    CarGenerationsActivity.this.startActivity(startPriceDetailsActivityIntent);

                }

            });
        }
        else{

            Intent startPriceDetailsActivityIntent = new Intent(CarGenerationsActivity.this, PriceDetailsActivity.class);
            startPriceDetailsActivityIntent.putExtra("url", url);
            startPriceDetailsActivityIntent.putExtra("name", name);
            CarGenerationsActivity.this.startActivity(startPriceDetailsActivityIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_generations, menu);
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
    private static List<String> keysAsList(Map<String, String> map) {
        List<String> list = new ArrayList<String>(map.keySet());
        return list;
    }
}
