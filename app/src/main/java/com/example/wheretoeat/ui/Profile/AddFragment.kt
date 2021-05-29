package com.example.wheretoeat.ui.Profile

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.data.MyDatabaseViewModel
import com.example.wheretoeat.data.User.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var myDatabaseViewModel: MyDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.button_add_user.setOnClickListener{
            insertDataToDatabase()
        }

        myDatabaseViewModel=ViewModelProvider(this).get(MyDatabaseViewModel::class.java)
        return view
    }

    private fun insertDataToDatabase() {
        val fName=editTextFN.text.toString()
        val lName=editTextLN.text.toString()
        val phone=editTextPhone.text.toString()
        val email=editTextTextEmailAddress.text.toString()

        if (validate(fName,lName,phone,email)){
            val user = User(0,fName,lName,phone,email)
            myDatabaseViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully registered", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.fragmentProfile)
        }
        else{
            Toast.makeText(requireContext(),"Fill out all the fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun validate(firstName : String, lastName : String, phoneNumber: String, email : String) : Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) &&
                TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(email))
    }

}