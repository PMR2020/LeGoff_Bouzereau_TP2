package com.centralelille.sequence1.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.centralelille.sequence1.R
import com.centralelille.sequence1.data.ItemToDo

class ItemAdapter(private val onItemListener: OnItemListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet: MutableList<ItemToDo> = mutableListOf()

    override fun getItemCount(): Int = dataSet.size

    fun showData(newDataSet: List<ItemToDo>) {
        dataSet.clear()
        dataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.liste, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("ItemAdapter", "onBindViewHolder $position")
        when (holder) {
            is ItemViewHolder -> holder.bind(dataSet[position])
        }
    }

    fun addItem(newItem: Any) {

    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.setOnClickListener {
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    val clickedItem = dataSet[itemPosition]
                    onItemListener.onItemClicked(clickedItem)
                }
            }
        }

        fun bind(item: ItemToDo) {
            title.text = item.label
        }
    }

    interface OnItemListener {
        fun onItemClicked(item: ItemToDo)
    }
}