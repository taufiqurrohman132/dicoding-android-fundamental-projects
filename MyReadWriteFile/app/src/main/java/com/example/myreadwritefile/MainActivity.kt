package com.example.myreadwritefile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myreadwritefile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNew.setOnClickListener(this)
        binding.buttonOpen.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_new -> newFile()
            R.id.button_open -> showList()
            R.id.button_save -> saveFile()
        }
    }

    private fun newFile(){
        binding.editTitle.setText("")
        binding.editFile.setText("")
        Toast.makeText(this, "Clearing file", Toast.LENGTH_SHORT).show()
    }

    private fun showList(){
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih file yang di inginkan")
        builder.setItems(items) { dialog, item ->
            loadData(items[item].toString())
        }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(title: String){
        val fileModel = FileHelper.readFromFile(this, title)
        binding.editTitle.setText(fileModel.fileName)
        binding.editFile.setText(fileModel.data)
        Toast.makeText(this, "Loading ${fileModel.fileName} data", Toast.LENGTH_SHORT).show()

    }

    private fun saveFile(){
        when{
            binding.editTitle.text.toString().isEmpty() ->
                Toast.makeText(this, "Title harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show()
            binding.editFile.text.toString().isEmpty() ->
                Toast.makeText(this, "Kontent harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show()
            else -> {
                val title = binding.editTitle.text.toString()
                val text = binding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.fileName = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this) /// proses menyimpan objek data clas
                Toast.makeText(this, "Saving " + fileModel.fileName + " file", Toast.LENGTH_SHORT).show()
            }
        }
    }
}