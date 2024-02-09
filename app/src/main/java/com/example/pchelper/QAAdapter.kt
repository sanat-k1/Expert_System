package com.example.pchelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QAAdapter(private val datalist : ArrayList<QA>): RecyclerView.Adapter<QAAdapter.ViewHolderClass>() {
    class ViewHolderClass(itemView: View):  RecyclerView.ViewHolder(itemView){
        val answers : TextView = itemView.findViewById(R.id.answer)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAAdapter.ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: QAAdapter.ViewHolderClass, position: Int) {
        var currentitem = datalist[position]
        holder.answers.text = currentitem.Answer

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}