package com.example.popmartcatalog

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengaktifkan fungsi tombol kembali
        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            // finish() akan menutup halaman ini dan kembali ke MainActivity
            finish()
        }
    }
}