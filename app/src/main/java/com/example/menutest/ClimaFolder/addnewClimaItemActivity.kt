package com.example.menutest.ClimaFolder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.menutest.R
import kotlinx.android.synthetic.main.activity_addnew_clima_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addnewClimaItemActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE_ADD_CLIMA_ITEM =1000
        const val EXTRA_CLIMA_ITEM = "clima_item"
        fun  startActivity(activity: AppCompatActivity){
            val intent = Intent(activity, addnewClimaItemActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE_ADD_CLIMA_ITEM)
        }
    }

    private  var climaitem = ClimaItem(-1, "","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnew_clima_item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_add_clima_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_none->{
                if (etnombre.text.isNullOrEmpty()){
                    Toast.makeText(this, "Porfavor escribe un nombre", Toast.LENGTH_LONG).show()
                    return true
                }

                if (etTipo.text.isNullOrEmpty()){
                    Toast.makeText(this, "Escribe un tipo de clima", Toast.LENGTH_LONG).show()
                    return true
                }

                climaitem.nombre = etnombre.text.toString()
                climaitem.tipo = etTipo.text.toString()

                addClimaItemToServer()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addClimaItemToServer(){
        progressBar.visibility = View.VISIBLE
        MyApp.getInstance().getApiServices().clima(climaitem).enqueue(object : Callback<ClimaItem> {
            override fun onResponse(call: Call<ClimaItem>, response: Response<ClimaItem>) {
                progressBar.visibility = View.GONE
                val data = Intent()
                data.putExtra(EXTRA_CLIMA_ITEM, climaitem)
                setResult(Activity.RESULT_OK, data)
                finish()
            }

            override fun onFailure(call: Call<ClimaItem>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@addnewClimaItemActivity, "Failed to post", Toast.LENGTH_LONG).show()
            }
        })

    }

}