package com.example.wheretoeat.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.databinding.LoadStateFooterBinding

//function type. and returns unit
class LoadStateFooterAdapter(private val retry :()->Unit) : LoadStateAdapter<LoadStateFooterAdapter.LoadStateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val  binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    //inner class so we can access properties of the surrounding class
    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding):
            RecyclerView.ViewHolder(binding.root){

        init {
            binding.buttonRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible=loadState is LoadState.Loading
                buttonRetry.isVisible=loadState !is LoadState.Loading
                textViewError.isVisible=loadState !is LoadState.Loading
            }
        }
    }
}