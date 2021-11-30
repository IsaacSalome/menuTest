package com.example.menutest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu;
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.menutest.ClimaFolder.ClimaActivity

class MainActivity : AppCompatActivity() {
    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   menuInflater.inflate(R.menu.menu_main, menu)

        if (item.getItemId() == R.id.menu_settings)
        {

            val intent = Intent(this, ClimaActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "hello")
            }
            startActivity(intent)

            Toast.makeText(this, "Configuración",

            Toast.LENGTH_LONG).show();

        }

        else

        {

            return super.onContextItemSelected(item);

        }

        return true;

    }
}