package com.example.riche_000.questboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuestList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        final Context context = this;

        ParseQuery query = new ParseQuery("Quest");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> objects,
                             ParseException e) {
                if (e == null) {
                    Log.d("Brand", "Retrieved " + objects.size() + " Brands");
                   /* for (ParseObject dealsObject : objects) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                    }*/
                    //Filter the list
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    List<ParseObject> filt = new ArrayList<ParseObject>();
                    for(ParseObject o: objects){
                        String username = o.getString("createdBy");
                        if(!(currentUser.getUsername().equals(username)))
                        {
                            filt.add(o);
                        }
                    }

                    ListAdapter listAdapter = new customList(context,filt);

                    ListView listView = (ListView) findViewById(R.id.questListView);
                    listView.setAdapter(listAdapter);

                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //String food = String.valueOf(parent.getItemAtPosition(position));
                                    ParseFile fileObject = objects.get(position).getParseFile("image");
                                    fileObject.getDataInBackground(new GetDataCallback() {
                                        public void done(byte[] data, ParseException e) {
                                            if (e == null) {
                                                Log.d("test", "We've got data in data.");
                                                // use data for something
                                                byte[] bitpic = data;
                                                Bitmap bitmap = BitmapFactory.decodeByteArray(bitpic , 0, bitpic.length);
                                               try {
                                                   BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("byte.dat"));
                                                   bos.write(bitpic);
                                                   bos.flush();
                                                   bos.close();
                                               }catch (IOException l){
                                                    l.printStackTrace();
                                               }
                                            } else {
                                                Log.d("test", "There was a problem downloading the data.");
                                            }
                                        }
                                    });


                                    Intent i = new Intent(context,Expand.class);
                                    ParseProxyObject obs = new ParseProxyObject(objects.get(position));
                                    i.putExtra("parseobs",obs);
                                    startActivity(i);

                                    //String food = String.valueOf(objects.get(position).getObjectId());
                                    //Toast.makeText(QuestList.this, food, Toast.LENGTH_SHORT).show();



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
