package com.example.popmartcatalog

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnSortAZ: Button
    private lateinit var btnSortZA: Button
    private lateinit var rvPopMart: RecyclerView
    private val list = ArrayList<PopMart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi Komponen UI dari XML
        edtSearch = findViewById(R.id.edt_search)
        btnSearch = findViewById(R.id.btn_search)
        btnSortAZ = findViewById(R.id.btn_sort_az)
        btnSortZA = findViewById(R.id.btn_sort_za)

        rvPopMart = findViewById(R.id.rv_popmart)
        rvPopMart.setHasFixedSize(true)

        // 2. Mengambil data awal dari gudang PopMartData
        list.addAll(PopMartData.listPopMart)

        // 3. Menampilkan daftar karakter pertama kali
        showRecyclerList()

        // 4. Logika Tombol Cari (Sequential Search)
        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString().trim()
            if (query.isEmpty()) {
                edtSearch.error = "Nama tidak boleh kosong!"
                showRecyclerList() // Tampilkan semua jika kosong
            } else {
                searchPopMart(query)
            }
        }

        // 5. Logika Tombol Urutkan A-Z (Ascending)
        btnSortAZ.setOnClickListener {
            list.sortBy { it.name }
            showRecyclerList()
            Toast.makeText(this, "Berhasil diurutkan A-Z", Toast.LENGTH_SHORT).show()
        }

        // 6. Logika Tombol Urutkan Z-A (Descending)
        btnSortZA.setOnClickListener {
            list.sortByDescending { it.name }
            showRecyclerList()
            Toast.makeText(this, "Berhasil diurutkan Z-A", Toast.LENGTH_SHORT).show()
        }
    }

    // Fungsi untuk menampilkan data ke RecyclerView
    private fun showRecyclerList() {
        rvPopMart.layoutManager = LinearLayoutManager(this)
        val listPopMartAdapter = ListPopMartAdapter(list)
        rvPopMart.adapter = listPopMartAdapter
    }

    // Implementasi Algoritma Sequential Search
    private fun searchPopMart(query: String) {
        val filteredList = ArrayList<PopMart>()

        for (item in list) {
            if (item.name.contains(query, ignoreCase = true) ||
                item.series.contains(query, ignoreCase = true)) {
                filteredList.add(item)
            }
        }

        // Update tampilan dengan hasil filter
        val listPopMartAdapter = ListPopMartAdapter(filteredList)
        rvPopMart.adapter = listPopMartAdapter

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Karakter '$query' tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
    }
}