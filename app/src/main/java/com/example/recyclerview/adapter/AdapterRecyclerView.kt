package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.data.DataRecyclerView

class AdapterRecyclerView(private val listData: ArrayList<DataRecyclerView>): RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gambar = itemView.findViewById<ImageView>(R.id.ivItemRv)
        val judul = itemView.findViewById<TextView>(R.id.tvJudul)
        val desc = itemView.findViewById<TextView>(R.id.tvDesc)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gambar.setImageResource(listData[position].ivGambar)
        holder.judul.text = listData[position].tvJudul
        holder.desc.text = listData[position].tvDesc
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}