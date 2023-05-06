package com.example.free_tution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.free_tution.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener {
            val name = binding.editTextTextPersonName5.text.toString()
            val date = binding.editTextDate3.text.toString()
            val time = binding.editTextTime2.text.toString()
            val place = binding.editTextTextPersonName8.text.toString()
            val phone = binding.editTextPhone2.text.toString()

            updateData(name,date,time,place,phone)
        }
    }

    private fun updateData(name: String, date: String, time: String, place: String, phone: String) {

        database = FirebaseDatabase.getInstance().getReference("users")
        val user = mapOf<String,String>(
            "name" to name,
            "date" to date,
            "time" to time,
            "place" to place,
            "phone" to phone
        )

        database.child(place).updateChildren(user).addOnSuccessListener {
            binding.editTextTextPersonName5.text.clear()
            binding.editTextDate3.text.clear()
            binding.editTextTime2.text.clear()
            binding.editTextTextPersonName8.text.clear()
            binding.editTextPhone2.text.clear()
            Toast.makeText(this,"Success to Update",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Faild to Update",Toast.LENGTH_SHORT).show()
        }

    }
}