package org.ko.carprices;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    ListView categoriesListView;
    Map<String, String> categoryMap = new HashMap<String, String>();
    String url = "http://allegro.pl/samochody-osobowe-4029";
    ArrayAdapter<String> adapter;

    protected void refreshList(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            categoryMap = new Categories().execute(url).get();
        }
        catch(Exception e) {

        }
        List<String> categoryList = keysAsList(categoryMap);
        categoriesListView = (ListView)findViewById(R.id.categoriesListView);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList);
        categoriesListView.setAdapter(adapter);


    categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String itemValue = (String) categoriesListView.getItemAtPosition(position);
            String[] params = new String[2];
            url = "http://allegro.pl"+categoryMap.get(itemValue).toString();
            String name = itemValue;
            Intent startModelActivityIntent = new Intent(MainActivity.this, CarModelActivity.class);
            startModelActivityIntent.putExtra("url",url);
            startModelActivityIntent.putExtra("name",name);
            MainActivity.this.startActivity(startModelActivityIntent);

        }

    });
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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