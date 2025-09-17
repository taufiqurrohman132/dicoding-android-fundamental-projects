package com.example.myviewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        binding.btnCalculate.setOnClickListener {
            val width = binding.edtWidth.text.toString()
            val height = binding.edtHeight.text.toString()
            val lenght = binding.edtLenght.text.toString()

            when{
                width.isEmpty() ->
                    binding.edtWidth.error = "Masih kosong"
                height.isEmpty() ->
                    binding.edtHeight.error = "Masih kosong"
                lenght.isEmpty() ->
                    binding.edtLenght.error = "Masih kosong"
                else -> {
                    viewModel.calculate(width, height, lenght)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult(){
        binding.tvResult.text = viewModel.result.toString()

    }
}