 package com.example.needclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.needclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchNeedItems:String=binding.searchNeedItems.text.toString()
            if (searchNeedItems.isEmpty()){
                readData(searchNeedItems)
            }else{
                Toast.makeText(this,"Please enter the need items",Toast.LENGTH_SHORT).show()
            }
        }
    }
     private fun readData(needItems:String){
         databaseReference= FirebaseDatabase.getInstance().getReference("Need Items")
         databaseReference.child(needItems).get().addOnSuccessListener {
             if (it.exists()){
                 val name= it.child("name").value
                 val position= it.child("position").value
                 val phone= it.child("phone").value

                 Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                 binding.searchNeedItems.text.clear()
                 binding.readName.text=name.toString()
                 binding.readPosition.text=position.toString()
                 binding.readPhone.text=phone.toString()
             }else{
                 Toast.makeText(this,"Need Items does not exist",Toast.LENGTH_SHORT).show()
             }
         }.addOnFailureListener{
             Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
         }
     }


}