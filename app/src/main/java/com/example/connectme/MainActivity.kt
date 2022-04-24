package com.example.connectme

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connectme.databinding.ActivityMainBinding
import com.example.connectme.network.GET
import com.example.connectme.network.NetworkHelper
import com.example.connectme.network.POST
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.FormBody
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.connectButton.setOnClickListener{onConnectClick()}
    }

    private fun  onConnectClick(){

                Toast.makeText(this@MainActivity, "Sending request...", Toast.LENGTH_SHORT).show()
        val thread = Thread {
            try {
                val client = NetworkHelper(context = this).client

                val formBody = FormBody.Builder()
                    .add("mode", "191")
                    .add("username", "student")
                    .add("password", "Student@123")
                    .add("producttype", "0")
                    .build()
                val header = Headers.headersOf("ContentType", "application/x-www-form-urlencoded")
                val f = POST("http://172.10.1.2:8090/login.xml", header, body = formBody)
                val response = client.newCall(f).execute()
                if(response.isSuccessful){
                val adfs = Json.decodeFromString<JsonObject>(response.body!!.string())
                Log.d("f", adfs.toString())
                }else{
                    Log.d("f", "failed")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }
}