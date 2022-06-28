package com.bitcode.webservices1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bitcode.webservices1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpleRequest.setOnClickListener {
            WebThread().execute(
                *arrayOf(
                    binding.edtTargetUrl.text.toString()
                )
            )
        }
        binding.btnGetUsers.setOnClickListener {
            WebThread().execute(null)
        }

    }
}