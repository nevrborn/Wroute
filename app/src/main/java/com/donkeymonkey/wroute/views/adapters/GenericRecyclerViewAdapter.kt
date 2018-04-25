package com.donkeymonkey.wroute.views.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.BR


/**
 * Created by Sebastiaan on 21-11-2017.
 */

class GenericRecyclerViewAdapter<T>(
        private val layoutId: Int,
        private val listener: InteractionListener<T>
) : RecyclerView.Adapter<GenericRecyclerViewAdapter<T>.ViewHolder>() {

    val items = mutableListOf<T>()

    fun addItem(item: T) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun updateItem(item: T) {
        val index = this.items.indexOf(item)
        this.items[index] = item
        notifyItemChanged(index)
    }

    fun removeItem(item: T) {
        this.items.remove(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericRecyclerViewAdapter<T>.ViewHolder, position: Int) {
        val item = this.items[position]
        holder.bindItem(item)

        holder.view.setOnClickListener(View.OnClickListener { listener.onItemSelected(holder.item) })
    }

    override fun getItemCount() = this.items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding = DataBindingUtil.bind(view)!!
        var item: T? = null

        fun bindItem(item: T) {
            this.item = item
            binding.setVariable(BR.model, this.item)
            binding.executePendingBindings()
        }
    }

    interface InteractionListener<T> {
        fun onItemSelected(item: T?)
    }

    companion object {
        val TAG = "GenericRecyclerViewAdapter"
    }
}