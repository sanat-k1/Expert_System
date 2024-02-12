package com.example.pchelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class gpu_adapter(private val datalist : ArrayList<gpu_re>):
    RecyclerView.Adapter<gpu_adapter.ViewHolderClass>(){
    class ViewHolderClass(itemView: View):  RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.cmlist)
        val text : TextView = itemView.findViewById(R.id.teeeeets)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): gpu_adapter.ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: gpu_adapter.ViewHolderClass, position: Int) {
        var currentitem = datalist[position]
        holder.image.setImageResource(currentitem.image)
        holder.text.text = currentitem.desc
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}