package com.ozanyazici.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ozanyazici.retrofitkotlin.databinding.RowLayoutBinding
import com.ozanyazici.retrofitkotlin.model.CryptoModel

class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    private val colors: Array<String> = arrayOf("#94e5ff","#00ff7f","#ffc0cb","#e387ff","#f28500","#f8ec0e","#14ea1c","#4c92d3")

    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.textName.text = cryptoList.get(position).currency
        holder.binding.textPrice.text = cryptoList.get(position).price
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context,"Clicked : ${cryptoList.get(position).currency}",Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
    }
}