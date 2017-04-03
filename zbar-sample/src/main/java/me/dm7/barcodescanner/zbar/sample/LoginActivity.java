package me.dm7.barcodescanner.zbar.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    CheckBox rememberCheck;

    SharedPreferences sharedpreferences;
    String user_pref;
    String passw_pref;
    Boolean remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //istanzio gli elementi grafici
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        rememberCheck = (CheckBox) findViewById(R.id.check_in);

        //recupera le preferenze se sono state precendentemente memorizzate
        sharedpreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user_pref = sharedpreferences.getString("USER_SAVED", "");
        passw_pref = sharedpreferences.getString("PASSW_SAVED", "");
        remember = sharedpreferences.getBoolean("REMEMBER", false);

        //se recupera delle preferenze le inserisce nel form
        if(remember == true && !user_pref.equals("") && !passw_pref.equals("")){
            username.setText(user_pref);
            password.setText(passw_pref);
            rememberCheck.setChecked(true);
        }

    }

    //onClick del bottone definito nel layout
    public void Login(View v){
        //salva le preferenze o le svuota in base al valore del CheckBox
        if(rememberCheck.isChecked()){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("USER_SAVED", username.getText().toString());
            editor.putString("PASSW_SAVED", password.getText().toString());
            editor.putBoolean("REMEMBER", true);
            editor.commit();
        }else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("USER_SAVED", "");
            editor.putString("PASSW_SAVED", "");
            editor.putBoolean("REMEMBER", false);
            editor.commit();
        }

        //pass to the ViewActivity
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        Bundle b = new Bundle();
        b.putString("username", username.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
        finish();


    }
}
