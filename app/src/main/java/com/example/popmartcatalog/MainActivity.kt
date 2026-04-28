package com.example.popmartcatalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager // Import tambahan
import androidx.recyclerview.widget.RecyclerView // Import tambahan

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnTestIntent: Button // Menggunakan tombol A-Z sementara
    private lateinit var rvPopMart: RecyclerView
    private val list = ArrayList<PopMart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi Komponen UI
        edtSearch = findViewById(R.id.edt_search)
        btnSearch = findViewById(R.id.btn_search)
        btnTestIntent = findViewById(R.id.btn_sort_az)

        // Pastikan ID ini sama dengan ID RecyclerView yang ada di activity_main.xml kamu
        rvPopMart = findViewById(R.id.rv_popmart)
        rvPopMart.setHasFixedSize(true)

        // 2. Mengambil data dari gudang PopMartData
        list.addAll(PopMartData.listPopMart)

        // 3. Menampilkan data ke layar
        showRecyclerList()

        // 4. Validasi Input (If-Else)
        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString().trim()
            if (query.isEmpty()) {
                edtSearch.error = "Nama karakter tidak boleh kosong!"
                Toast.makeText(this@MainActivity, "Masukkan nama produk Pop Mart", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Mencari: $query", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Navigasi Intent (Pindah Halaman)
        btnTestIntent.setOnClickListener {
            val intentKeDetail = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intentKeDetail)
        }
    } // <-- Ini adalah batas akhir dari onCreate()

    // PERBAIKAN: Fungsi ini diletakkan di LUAR onCreate()
    private fun showRecyclerList() {
        rvPopMart.layoutManager = LinearLayoutManager(this)
        val listPopMartAdapter = ListPopMartAdapter(list)
        rvPopMart.adapter = listPopMartAdapter
    }
}