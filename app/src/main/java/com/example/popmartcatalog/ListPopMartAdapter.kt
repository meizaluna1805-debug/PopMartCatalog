package com.example.popmartcatalog

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ListPopMartAdapter(private val listPopMart: ArrayList<PopMart>) : RecyclerView.Adapter<ListPopMartAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_popmart)
        val tvName: TextView = itemView.findViewById(R.id.txt_nama_karakter)
        val tvSeries: TextView = itemView.findViewById(R.id.txt_series)
        val tvPrice: TextView = itemView.findViewById(R.id.txt_harga)
        val tvStatus: TextView = itemView.findViewById(R.id.txt_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_popmart, parent, false)
        return ListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, series, price, status, image) = listPopMart[position]

        holder.imgPhoto.setImageResource(image)
        holder.tvName.text = name
        holder.tvSeries.text = series
        holder.tvStatus.text = status

        val formatRupiah = java.text.NumberFormat.getNumberInstance(Locale.forLanguageTag("id-ID"))
        holder.tvPrice.text = "Rp ${formatRupiah.format(price)}"

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_POPMART, listPopMart[holder.bindingAdapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listPopMart.size
}