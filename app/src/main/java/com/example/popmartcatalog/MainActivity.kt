package com.example.popmartcatalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnTestIntent: Button // Menggunakan tombol A-Z sementara

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi Komponen UI
        edtSearch = findViewById(R.id.edt_search)
        btnSearch = findViewById(R.id.btn_search)
        btnTestIntent = findViewById(R.id.btn_sort_az)

        // 2. Validasi Input (If-Else)
        btnSearch.setOnClickListener {

            val query = edtSearch.text.toString().trim()
            if (query.isEmpty()) {
                edtSearch.error = "Nama karakter tidak boleh kosong!"
                Toast.makeText(this@MainActivity, "Masukkan nama produk Pop Mart", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Mencari: $query", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Navigasi Intent (Pindah Halaman)
        btnTestIntent.setOnClickListener {
            val intentKeDetail = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intentKeDetail)
        }
    }
}