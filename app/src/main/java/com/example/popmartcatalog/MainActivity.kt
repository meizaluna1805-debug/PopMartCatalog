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

        // 4. Validasi Input dan Panggil Algoritma Search
        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString().trim()
            if (query.isEmpty()) {
                edtSearch.error = "Nama karakter tidak boleh kosong!"
                Toast.makeText(this@MainActivity, "Masukkan nama produk Pop Mart", Toast.LENGTH_SHORT).show()

                // Jika kosong, kembalikan tampilan ke daftar semula (semua karakter)
                showRecyclerList()
            } else {
                // Jalankan algoritma pencarian
                searchPopMart(query)
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

    // Algoritma Sequential Search
    private fun searchPopMart(query: String) {
        val filteredList = ArrayList<PopMart>() // Keranjang baru untuk hasil pencarian

        // Mengecek satu per satu data di gudang utama
        for (item in list) {
            // Jika nama atau seri mengandung kata yang dicari (ignoreCase = abaikan huruf besar/kecil)
            if (item.name.contains(query, ignoreCase = true) || item.series.contains(query, ignoreCase = true)) {
                filteredList.add(item) // Masukkan ke keranjang hasil
            }
        }

        // Panggil Adapter lagi untuk menampilkan keranjang hasil pencarian ke layar
        val listPopMartAdapter = ListPopMartAdapter(filteredList)
        rvPopMart.adapter = listPopMartAdapter

        // Jika keranjang kosong (tidak ketemu)
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Karakter '$query' tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
    }
}