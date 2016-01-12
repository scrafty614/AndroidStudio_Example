package com.noctisdrakon.plifmx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Empezamos con las shardprefferences para manejar las sesiones
        final String tag = "MainActivity";
        Log.d(tag,"Aqui Empieza el MainActivity");

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Intent i = new Intent(this, LoginActivity.class); //Este es el intent de la actividad de login
        Log.d(tag,"Pasamos las SP");

        if(!preferences.contains("id")){
            //Si no hay ID en las shared prefferences, iniciamos la actividad de login/registro
            Log.d(tag,"Entramos a la condicion");
            startActivity(i);
            finish();
        }

        Log.d(tag, "Pasamos la condicion");
        TextView userid= (TextView) findViewById(R.id.userid);
        Log.d(tag,"TV userid");
        TextView useremail = (TextView) findViewById(R.id.useremail);
        Log.d(tag, "TV useremail");

        Button logout = (Button) findViewById(R.id.cerrarses);
        Log.d(tag, "BT logout");


        useremail.setText(preferences.getString("email", "undefined"));
        Log.d(tag, "GSP email");
        int gotid = preferences.getInt("id", 0);
        userid.setText(String.valueOf(gotid));
        Log.d(tag, "GSP id");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se borran todos los datos almacenados de la sesion
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                //volvemos al login y terminamos esta actividad
                startActivity(i);
                finish();
            }
        });
        Log.d(tag, "OCL logout");



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
