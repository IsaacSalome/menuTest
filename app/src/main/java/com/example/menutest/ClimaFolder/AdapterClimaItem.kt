package com.example.menutest.ClimaFolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.menutest.R
import kotlinx.android.synthetic.main.listitem_clima.view.*


class AdapterClimaItem : RecyclerView.Adapter<AdapterClimaItem.MyViewHolder>() {
    private var list = ArrayList<ClimaItem>()
    var onDeleteItemClickListener:onDeleteItemCliCkListener?=null

    interface onDeleteItemCliCkListener{
        fun onDeleteItem(climaItem: ClimaItem, position: Int)
    }

    inner  class MyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.listitem_clima, parent, false)){
        fun bind(climaItem: ClimaItem) = with(itemView){
            textnombre.text = climaItem.nombre
            textTipo.text = climaItem.tipo
            btnDelete.setOnClickListener{
                onDeleteItemClickListener?.onDeleteItem(climaItem, adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<ClimaItem>){
        this.list = list
        notifyDataSetChanged()
    }

    fun addItem(climaItem: ClimaItem){
        list.add(climaItem)
        notifyItemInserted(list.size - 1)
    }

    fun deleteItem(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}
