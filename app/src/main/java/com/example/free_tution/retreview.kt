package com.example.free_tution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.free_tution.databinding.ActivityRetreviewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class retreview : AppCompatActivity() {

    private lateinit var binding: ActivityRetreviewBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val place : String = binding.splace.text.toString()
            if (place.isNotEmpty()){
                readeData(place)
            }else{
                Toast.makeText(this,"please enter the name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readeData(place: String){

        database = FirebaseDatabase.getInstance().getReference("users")
        database.child(place).get().addOnSuccessListener {
            if (it.exists()){
               //get all data
                val name = it.child("name").value
                val subject = it.child("subject").value
                val date = it.child("date").value
                val time = it.child("time").value
                val place = it.child("place").value
                val phone = it.child("phone").value
                Toast.makeText(this,"Successfull",Toast.LENGTH_SHORT).show()

                binding.splace.text.clear()
                binding.name.text = name.toString()
                binding.sub.text = subject.toString()
                binding.date.text = date.toString()
                binding.time.text = time.toString()
                binding.place.text = place.toString()
                binding.phone.text = phone.toString()


            }else{
                Toast.makeText(this,"Dosen't exit",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}