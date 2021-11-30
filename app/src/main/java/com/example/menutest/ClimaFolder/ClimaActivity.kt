package com.example.menutest.ClimaFolder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menutest.R
import kotlinx.android.synthetic.main.activity_clima.*
import retrofit2.Call
import retrofit2.Response

class ClimaActivity : AppCompatActivity(), AdapterClimaItem.onDeleteItemCliCkListener  {
    private val adapterClimaItem = AdapterClimaItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapterClimaItem
        adapterClimaItem.onDeleteItemClickListener = this@ClimaActivity
        faAddclimaitem.setOnClickListener{
            addnewClimaItemActivity.startActivity(this)
        }

    MyApp.getInstance().getApiServices().clima().enqueue(object : retrofit2.Callback<List<ClimaItem>>{
        override fun onResponse(
            call: Call<List<ClimaItem>>,
            response: Response<List<ClimaItem>>
        ) {
            if (response.isSuccessful && !response.body().isNullOrEmpty()){
                adapterClimaItem.setList(response.body() as ArrayList<ClimaItem>)
            }else{
                Toast.makeText(this@ClimaActivity, "Error loading list", Toast.LENGTH_LONG).show()
            }
        }

        override fun onFailure(call: Call<List<ClimaItem>>, t: Throwable) {
            Toast.makeText(this@ClimaActivity, "Error loading list", Toast.LENGTH_LONG).show()

        }

    })
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK){
        if(requestCode == addnewClimaItemActivity.REQUEST_CODE_ADD_CLIMA_ITEM){
            data?.let {
                val item = data.getParcelableExtra<ClimaItem>(addnewClimaItemActivity.EXTRA_CLIMA_ITEM)
                adapterClimaItem.addItem(item!!)
            }
        }
    }

}

override fun onDeleteItem(climaItem: ClimaItem, position: Int) {
    MyApp.getInstance().getApiServices().deleteclimaItem(climaItem.id).enqueue(object: retrofit2.Callback<DeleteResponse>{
        override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
            if (response.isSuccessful && response.body()!= null){
                Toast.makeText(this@ClimaActivity,response.body()?.response, Toast.LENGTH_LONG).show()
                adapterClimaItem.deleteItem(position)
            }else{
                Toast.makeText(this@ClimaActivity, "Error deleting item", Toast.LENGTH_LONG).show()
            }
        }

        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
            Toast.makeText(this@ClimaActivity,"Error deleting item", Toast.LENGTH_LONG).show()
        }

    })
}
}