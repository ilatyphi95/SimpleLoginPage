package com.ilatyphi95.simpleloginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String HAS_ACCOUNT = "has_account";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String PREFERENCES = "preferences";
    private EditText mEtUsername;
    private EditText mEtPassword;
    SharedPreferences mSharedPreferences;
    private Boolean mHasAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        mEtUsername = findViewById(R.id.et_user_name);
        mEtPassword = findViewById(R.id.et_password);
        Button btnEnter = findViewById(R.id.btn_enter);

        mHasAccount = mSharedPreferences.getBoolean(HAS_ACCOUNT, false);
        if(!mHasAccount) {
            btnEnter.setText(R.string.sign_up);
        } else {
            btnEnter.setText(R.string.login);
        }

        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Boolean validInputs = true;
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        if(username.isEmpty()) {
            toast.cancel();
            toast = Toast.makeText(this, "Invalid username", Toast.LENGTH_LONG);
            toast.show();
            validInputs = false;
        }

        if(password.isEmpty()){
            toast.cancel();
            toast = Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG);
            toast.show();
            validInputs = false;
        }

        if(validInputs) {
            if(mHasAccount) {
                String savedUsername = mSharedPreferences.getString(USER_NAME, "");
                String savedPassword = mSharedPreferences.getString(PASSWORD, "");
                if(username.equalsIgnoreCase(savedUsername) && password.equalsIgnoreCase(savedPassword)) {
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                } else {
                    toast.cancel();
                    toast = Toast.makeText(this, "Invalid username or Password", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                SharedPreferences.Editor  editor = mSharedPreferences.edit();
                editor.putString(USER_NAME, username);
                editor.putString(PASSWORD, password);
                editor.putBoolean(HAS_ACCOUNT, true);
                editor.apply();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        }
    }
}
