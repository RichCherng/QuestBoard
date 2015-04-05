package com.example.riche_000.questboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class QuestList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        final Context context = this;

        ParseQuery query = new ParseQuery("Quest");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects,
                             ParseException e) {
                if (e == null) {
                    Log.d("Brand", "Retrieved " + objects.size() + " Brands");
                   /* for (ParseObject dealsObject : objects) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                    }*/
                    ListAdapter listAdapter = new customList(context,objects);

                    ListView listView = (ListView) findViewById(R.id.questListView);
                    listView.setAdapter(listAdapter);

                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String food = String.valueOf(parent.getItemAtPosition(position));
                                    //Toast.makeText(QuestList.this, food, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(context,Expand.class);
                                    startActivity(i);
                                }
                            }
                    );

                } else {
                    Log.d("Brand", "Error: " + e.getMessage());
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quest_list, menu);
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
