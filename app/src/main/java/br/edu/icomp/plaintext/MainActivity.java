package br.edu.icomp.plaintext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    public void entrarClicado(View view){
//        EditText inputLogin = findViewById(R.id.loginEditText);
//        String login = inputLogin.getText().toString();
//
//        Intent intent = new Intent(this, ListActivity.class);
//        intent.putExtra("login", login);
//        startActivity(intent);
//
//    }
    public void entrarClicado(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefLogin = sharedPreferences.getString("login", "");
        String prefPass  = sharedPreferences.getString("password", "");

        String editLogin = ((EditText) findViewById(R.id.loginEditText)).getText().toString();
        String editPass  = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        if (editLogin.equals(prefLogin) && editPass.equals(prefPass)) {
            Intent intent = new Intent(this, ListActivity.class);
            EditText inputLogin = findViewById(R.id.loginEditText);
            intent.putExtra("login", inputLogin.getText().toString());
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Login/senha inválidos!", Toast.LENGTH_SHORT).show();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("PlainText Password Manager v1.0")
                    .setNeutralButton("Ok", null)
                    .show();
            return true;

        } else if(id == R.id.configs){
            Intent intentConfig = new Intent(this, PreferencesActivity.class);
            startActivity(intentConfig);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



}