package com.ilatyphi95.simpleloginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvDisplay = findViewById(R.id.tv_display);

        mPreferences = getSharedPreferences(MainActivity.PREFERENCES, MODE_PRIVATE);
        String username = getString(R.string.welcome) + ", " +
                mPreferences.getString(MainActivity.USER_NAME, "");
        tvDisplay.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch(itemId) {
            case R.id.action_reset:
                SharedPreferences.Editor editor  = mPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                break;
            case R.id.action_previous:
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                break;
        }
        return true;
    }
}
