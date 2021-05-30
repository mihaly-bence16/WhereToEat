package com.example.wheretoeat.ui.Favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheretoeat.R
import com.example.wheretoeat.data.Favorite.Favorite
import com.example.wheretoeat.data.MyDatabaseViewModel
import com.example.wheretoeat.data.model.Restaurant
import com.example.wheretoeat.ui.Favorite.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite_view.view.*

class FragmentFavorite : Fragment(), FavoriteAdapter.OnItemClickListener {

    private lateinit var myDatabaseViewModel: MyDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favorite_view, container, false)

        val adapter= FavoriteAdapter(this)
        val recyclerView = view.recycler_view_favorites
        recyclerView.adapter=adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        myDatabaseViewModel = ViewModelProvider(this).get(MyDatabaseViewModel::class.java)
        myDatabaseViewModel.getCurrentUserId().observe(viewLifecycleOwner, Observer { id ->
            if (id>0){
                myDatabaseViewModel.readAllFavoritesByCurrentId(id).observe(viewLifecycleOwner, Observer { returned ->
                        adapter.setData(returned)
                    })
            }
        })

        return view
    }

    override fun onItemClick(favorite: Favorite) {
        val restaurant = Restaurant(favorite.restaurant_id,favorite.name,favorite.address,favorite.city,favorite.state,
            favorite.area,favorite.postal_code,favorite.country,favorite.phone,favorite.lat,favorite.lng,
            favorite.price,favorite.reserve_url,favorite.mobile_reserve_url,favorite.image_url)
        val action = FragmentFavoriteDirections.actionFavoriteViewToDetailsFragment(restaurant)
        findNavController().navigate(action)
    }
}

