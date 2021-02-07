package com.example.wheretoeat.ui.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import kotlinx.android.synthetic.main.fragment_profile.view.*


class FragmentProfile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_profile, container, false)

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentProfile_to_addFragment)
        }

        return view
    }

}