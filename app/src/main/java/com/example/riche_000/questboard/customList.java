package com.example.riche_000.questboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class customList extends ArrayAdapter<ParseObject> {


    List<ParseObject> res;
    View customView;

    public customList(Context context,List<ParseObject> resource){//List<ParseObject> resource) {
        super(context,R.layout.feedquest, resource);
        res = resource;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.feedquest, parent, false);

        TextView feedView = (TextView) customView.findViewById(R.id.listText);
        final ImageView img = (ImageView) customView.findViewById(R.id.picView);

        String singleDescription = (res.get(position)).getString("title");

        ParseFile fileObject = res.get(position).getParseFile("image");
        fileObject.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    Log.d("test", "We've got data in data.");
                    // use data for something
                    byte[] bitpic = data;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bitpic , 0, bitpic.length);
                    img.setImageBitmap(bitmap);
                } else {
                    Log.d("test", "There was a problem downloading the data.");
                }
            }
        });

        feedView.setText(singleDescription);
        //img.setImageResource(R.drawable.moon);

        return customView;
    }
}
