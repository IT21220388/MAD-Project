package com.example.free_tution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.free_tution.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class delete : AppCompatActivity() {

    private lateinit var  binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.delBtn.setOnClickListener {

            var place = binding.sdplace.text.toString()
            if (place.isNotEmpty())
                deleteData(place)
            else{
                Toast.makeText(this,"Please enter the place", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(place: String) {

        database = FirebaseDatabase.getInstance().getReference("users")
        database.child(place).removeValue().addOnSuccessListener {

            binding.sdplace.text.clear()
            Toast.makeText(this,"Successfuly Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }
}