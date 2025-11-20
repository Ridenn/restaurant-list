package com.lucas.restaurantlist.features.storefeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lucas.restaurantlist.R
import com.lucas.restaurantlist.data.model.StoreResponse

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter: RecyclerView.Adapter<StoreItemViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<StoreResponse>() {
        override fun areItemsTheSame(oldItem: StoreResponse, newItem: StoreResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoreResponse, newItem: StoreResponse): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val store = listDiffer.currentList[position]
        holder.bind(store)
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitList(storeFeed: List<StoreResponse>) = listDiffer.submitList(storeFeed)
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(store: StoreResponse) {
        itemView.findViewById<TextView>(R.id.name).text = store.name
        itemView.findViewById<TextView>(R.id.description).text = store.description
    }
}
