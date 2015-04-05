package com.example.riche_000.questboard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Initializze Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8xbBV0VSPvp3IFabbFCeA4Q9REue38x6mCBo35mc", "N2YpkaQD2HFWwxFz953WB0JywWhGtmu12waHAh0n");

        //Check for existing User
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            loadLoginView();
        }



    }

    /**
     * if there're no existing user then redirect to login
     */
    public void loadLoginView(){
        Intent intent = new Intent(this, login.class);
        //Clear stack history, loging become main
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void goToComment(View view){
        Intent i = new Intent(this, Rating.class);
        startActivity(i);
    }

    public void QuestLog(View view){
        Intent i = new Intent(this, QuestList.class);
        startActivity(i);
    }

    public void CreateQuest(View view){
        Intent i = new Intent(this, CreateQuest.class);
        startActivity(i);
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
        else if(id == R.id.action_logout){
            ParseUser.logOut();
            loadLoginView();
        }


        return super.onOptionsItemSelected(item);
    }
}
