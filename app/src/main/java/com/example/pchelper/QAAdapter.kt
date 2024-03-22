package com.example.pchelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pchelper.roomdb.user

class QAAdapter: RecyclerView.Adapter<QAAdapter.ViewHolderClass>() {
    private var userlist =  emptyList<user>()
    class ViewHolderClass(itemView: View):  RecyclerView.ViewHolder(itemView){
        val question : TextView = itemView.findViewById(R.id.name)
        val answers : TextView = itemView.findViewById(R.id.answer)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAAdapter.ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: QAAdapter.ViewHolderClass, position: Int) {
        var currentitem = userlist[position]
        holder.answers.text = currentitem.answer
        holder.question.text = currentitem.question

    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    fun setdata(user: List<user>)
    {
        this.userlist=user
        notifyDataSetChanged()
    }

}