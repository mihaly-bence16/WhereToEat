package com.example.wheretoeat.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.wheretoeat.R
import com.example.wheretoeat.data.model.Restaurant
import com.example.wheretoeat.databinding.RestaurantViewBinding

class RestaurantAdapter (private val listener: OnItemClickListener):
    PagingDataAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(RESTAURANT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RestaurantViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    inner class RestaurantViewHolder(private val binding: RestaurantViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item!=null){
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(restaurant: Restaurant) {
            binding.apply {
                Glide.with(itemView)
                    .load(restaurant.image_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageView)
                textViewRestaurantName.text=restaurant.name
                textViewAddress.text=restaurant.address
                textViewPrice.text=restaurant.price.toString()
            }
        }
    }

    interface  OnItemClickListener{
        fun onItemClick(restaurant: Restaurant)
    }
    companion object {
        private val RESTAURANT_COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem.id == newItem.id

            //data classes auto generate equals method
            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem == newItem
        }
    }
}