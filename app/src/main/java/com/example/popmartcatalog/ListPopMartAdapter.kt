package com.example.popmartcatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPopMartAdapter(private val listPopMart: ArrayList<PopMart>) : RecyclerView.Adapter<ListPopMartAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ID sekarang SUDAH SINKRON dengan item_popmart.xml kamu!
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_popmart)
        val tvName: TextView = itemView.findViewById(R.id.txt_nama_karakter)
        val tvSeries: TextView = itemView.findViewById(R.id.txt_series)
        val tvPrice: TextView = itemView.findViewById(R.id.txt_harga)
        val tvStatus: TextView = itemView.findViewById(R.id.txt_status) // Aku tambahkan sekalian untuk status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_popmart, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, series, price, status, image) = listPopMart[position]

        // Memasukkan data dari gudang ke dalam desain XML
        holder.imgPhoto.setImageResource(image)
        holder.tvName.text = name
        holder.tvSeries.text = series
        holder.tvStatus.text = status

        // Menambahkan teks "Rp" agar harga tampil cantik
        holder.tvPrice.text = "Rp $price"
    }

    override fun getItemCount(): Int = listPopMart.size
}