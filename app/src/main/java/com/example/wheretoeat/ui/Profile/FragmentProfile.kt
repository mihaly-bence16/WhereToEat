package com.example.wheretoeat.ui.Profile

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
import com.example.wheretoeat.data.MyDatabaseViewModel
import com.example.wheretoeat.data.User.CurrentUser
import com.example.wheretoeat.data.User.User
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*


class FragmentProfile : Fragment() , ProfileAdapter.OnItemClickListener{

    private lateinit var myDatabaseViewModel : MyDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_profile, container, false)

        val adapter=ProfileAdapter(this)
        val recyclerView=view.recycler_view_users
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        myDatabaseViewModel=ViewModelProvider(this).get(MyDatabaseViewModel::class.java)
        myDatabaseViewModel.readAllUsers.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })
        myDatabaseViewModel.getCurrentUserId().observe(viewLifecycleOwner, Observer { returned ->
            if (returned>0) {
                myDatabaseViewModel.getUserById(returned)
                    .observe(viewLifecycleOwner, Observer { user ->
                        setTextView(user)
                    })
            }
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentProfile_to_addFragment)
        }
        return view
    }

    override fun onItemClick(user: User) {
        val date = Date().time
        val currentUser = CurrentUser(0,user.id, date)
        myDatabaseViewModel.deleteCurrentUser()
        myDatabaseViewModel.addCurrentUser(currentUser)
        setTextView(user)
    }
    private fun setTextView(user: User){
        view?.text_view_FN?.text=user.firstName
        view?.text_view_LN?.text=user.lastName
        view?.text_view_email?.text=user.email
        view?.text_view_phone?.text=user.phoneNumber
    }
}