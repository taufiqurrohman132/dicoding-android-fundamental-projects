package com.example.myunittest

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myunittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel(CuboidModel())

        binding.btnSave.setOnClickListener(this)
        binding.btnCalculateSurfaceArea.setOnClickListener(this)
        binding.btnCalculateVolume.setOnClickListener(this)
        binding.btnCalculateCircumference.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val length = binding.edtLength.text.toString().trim()
        val width = binding.edtWidth.text.toString().trim()
        val height = binding.edtHeight.text.toString().trim()

        when{
            TextUtils.isEmpty(length) -> {
                binding.edtLength.error = "Field ini tidak boleh kosong"
            }
            TextUtils.isEmpty(width) -> {
                binding.edtWidth.error = "Field ini tidak boleh kosong"
            }
            TextUtils.isEmpty(height) -> {
                binding.edtHeight.error = "Field ini tidak boleh kosong"
            }

            else -> {
                val valueLenght = length.toDouble()
                val valueWidth = width.toDouble()
                val valueHeight = height.toDouble()

                when(p0?.id){
                    R.id.btn_save -> {
                        viewModel.save(valueWidth, valueLenght, valueHeight)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        binding.tvResult.text = viewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        binding.tvResult.text = viewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        binding.tvResult.text = viewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        binding.btnCalculateVolume.visibility = View.VISIBLE
        binding.btnCalculateCircumference.visibility = View.VISIBLE
        binding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
    }

    private fun gone() {
        binding.btnCalculateVolume.visibility = View.GONE
        binding.btnCalculateCircumference.visibility = View.GONE
        binding.btnCalculateSurfaceArea.visibility = View.GONE
        binding.btnSave.visibility = View.VISIBLE
    }
}