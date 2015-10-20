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


public class CarModelActivity extends ActionBarActivity {
    ListView carModelListView;
    Map<String, String> modelMap = new HashMap<String, String>();
    ArrayAdapter<String> adapter;
    String url = new String();
    String name = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model);

        try {
            Intent intent = getIntent();
            url = intent.getStringExtra("url");
            name = intent.getStringExtra("name");
            setTitle(name);
            modelMap = new Models().execute(url).get();
        }
        catch(Exception e) {

        }
        List<String> modelList = keysAsList(modelMap);
        carModelListView = (ListView)findViewById(R.id.carModelListView);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modelList);
        carModelListView.setAdapter(adapter);

        carModelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) carModelListView.getItemAtPosition(position);
                url = "http://allegro.pl" + modelMap.get(itemValue).toString();

                Intent startModelActivityIntent = new Intent(CarModelActivity.this, CarGenerationsActivity.class);
                startModelActivityIntent.putExtra("url", url);
                startModelActivityIntent.putExtra("name", itemValue);

                CarModelActivity.this.startActivity(startModelActivityIntent);

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_model, menu);
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
