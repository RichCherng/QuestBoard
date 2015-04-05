package com.example.riche_000.questboard;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;


public class CreateQuest extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Bitmap photo;
    EditText des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);


        Button imgButton = (Button) findViewById(R.id.takePic);
        imageView = (ImageView) findViewById(R.id.imageView);
        des = (EditText) findViewById(R.id.des);

        //Disable button if user has no camera
        if(!hasCamera()){
            imgButton.setEnabled(false);
        }

    }

    //check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void postQuest(View view){
        ParseObject quest = new ParseObject("Quest");
        //quest.put("image",imageView);

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imageView);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image = stream.toByteArray();

        ParseFile file = new ParseFile("resume.png", image);

        quest.put("image",file);
        quest.put("description", des.getText().toString());
        quest.saveInBackground();
        finish();
    }

    //launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass result along to onActivityResult
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    //if you want to return the image taken


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extra = data.getExtras();
            photo = (Bitmap) extra.get("data");
            imageView.setImageBitmap(photo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_quest, menu);
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