package com.example.popmartcatalog

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POPMART = "extra_popmart"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Mengaktifkan Fungsi Tombol Kembali
        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        // 2. Inisialisasi Komponen UI
        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvSeries: TextView = findViewById(R.id.tv_detail_series)
        val tvPrice: TextView = findViewById(R.id.tv_detail_price)
        val tvStatus: TextView = findViewById(R.id.tv_detail_status)

        // 3. Menerima Bungkusan Data Parcelable
        val popMart = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_POPMART, PopMart::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_POPMART)
        }

        // 4. Mengisi Data ke Layar
        if (popMart != null) {
            imgPhoto.setImageResource(popMart.image)
            tvName.text = popMart.name
            tvSeries.text = popMart.series

            // Format Rupiah
            val formatRupiah = java.text.NumberFormat.getNumberInstance(java.util.Locale("id", "ID"))
            tvPrice.text = "Rp ${formatRupiah.format(popMart.price)}"

            tvStatus.text = popMart.status

            // 5. Logika Status Dinamis (Tersedia vs Habis)
            if (popMart.status.equals("Tersedia", ignoreCase = true)) {
                // Jika tersedia, pakai background aslimu
                tvStatus.setBackgroundResource(R.drawable.bg_status_tersedia)
            } else {
                // Jika tulisannya apa saja selain "Tersedia" (misal: "Habis"), warnanya jadi abu-abu
                tvStatus.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            }
        }
    }
}