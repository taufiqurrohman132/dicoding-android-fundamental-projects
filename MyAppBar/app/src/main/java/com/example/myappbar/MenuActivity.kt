package com.example.myappbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myappbar.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            searrhView.setupWithSearchBar(searchBar) //untuk menghubungkan seview dengan sercbar
            searrhView
                .editText
                .setOnEditorActionListener { textView, actionId, event -> // menambahkan aksi ketika di submit
                    searchBar.setText(searrhView.text) // mengambil tek dari serview
                    searrhView.hide() // serview di sembunyikan lagi
                    Toast.makeText(this@MenuActivity, searrhView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
        // contoh menambahkan option menu pada serach bar, atau searview juga bisa, caranya juga seperti ini
        binding.searchBar.inflateMenu(R.menu.opstion_menu)
        binding.searchBar.setOnMenuItemClickListener { _ ->
            Toast.makeText(this@MenuActivity, "klik", Toast.LENGTH_SHORT).show()
            true
        }
    }
}