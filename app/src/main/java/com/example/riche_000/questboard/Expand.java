package com.example.riche_000.questboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class Expand extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        Intent intent = getIntent();
        ParseProxyObject obs = (ParseProxyObject) intent.getSerializableExtra("parseobs");
        getIntent().getSerializableExtra("MyClass");
        TextView title,userID,time,date,timepost,location;
        ImageView img = (ImageView) findViewById(R.id.expand_img);

        title = (TextView) findViewById(R.id.expand_title);
        userID = (TextView) findViewById(R.id.expand_ID);
        time = (TextView) findViewById(R.id.expand_time);
        timepost = (TextView) findViewById(R.id.expand_time_posted);
        location = (TextView) findViewById(R.id.expand_location);


        title.setText(obs.getString("title"));
        userID.setText(obs.getString("createdBy"));
        time.setText(obs.getString("time"));
        timepost.setText(obs.getString("createdAt"));
        location.setText(obs.getString("location"));
       // byte[] bitpic ;//= MediaStore.Files.readAllBytes("byte.dat");

        /*File file = new File("byte.dat");
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        img.setImageBitmap(bitmap);*/
        /*ParseFile fileObject = obs.getParseFile("image");
        fileObject.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    Log.d("test", "We've got data in data.");
                    // use data for something
                    byte[] bitpic = data;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bitpic, 0, bitpic.length);
                    img.setImageBitmap(bitmap);
                } else {
                    Log.d("test", "There was a problem downloading the data.");
                }
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expand, menu);
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
