package com.example.wheretoeat.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wheretoeat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(R.layout.fragment_restaurant_list){
    private val viewModel by viewModels<ListViewModel> ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //important to pass viewLifecycleOwner instead this fragment because
        //you want to stop updating the ui when the view of the fragment is destroyed
        viewModel.reestaurants.observe(viewLifecycleOwner){

        }
    }
}