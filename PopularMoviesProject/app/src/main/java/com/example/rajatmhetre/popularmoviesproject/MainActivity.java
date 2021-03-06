package com.example.rajatmhetre.popularmoviesproject;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity{

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ArrayList<MovieItem> movieList = new ArrayList<MovieItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                switch (item.getItemId()) {
                    case R.id.action_refresh: {
                        final GridView movieGrid = (GridView) findViewById(R.id.movieGrid);
                        progressBar = (ProgressBar) findViewById(R.id.progressBar);

                            FetchMovieData movieData;
                            movieData = new FetchMovieData(getApplicationContext(), new AsyncResponse() {
                                @Override
                                public void getMovieList(ArrayList<MovieItem> movieListObject) {
                                    movieList = movieListObject;
                                    movieGrid.setAdapter(new MovieItemAdapter(getApplicationContext(), movieList));
                                }
                            });
                            movieData.execute();


                        break;
                    }
                    case R.id.action_settings: {
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        break;
                    }
                }
                return true;
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

}
