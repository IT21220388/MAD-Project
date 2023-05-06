package com.example.free_tution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.Toast
import com.example.free_tution.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.subbtn.setOnClickListener {

            val name = binding.name.text.toString()
            val subject = binding.subject.text.toString()
            val date = binding.date.text.toString()
            val time = binding.time.text.toString()
            val place = binding.place.text.toString()
            val phone = binding.phone.text.toString()

            database = FirebaseDatabase.getInstance().getReference("users")
            val user = user(name,subject,date,time,place,phone)
            database.child(place).setValue(user).addOnSuccessListener {

               //clear data
                binding.name.text?.clear()
                binding.subject.text?.clear()
                binding.date.text?.clear()
                binding.time.text?.clear()
                binding.place.text?.clear()
                binding.phone.text?.clear()

                Toast.makeText(this,"Successfully Submited",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }


    }
}