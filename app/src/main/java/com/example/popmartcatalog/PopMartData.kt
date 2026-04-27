package com.example.popmartcatalog

object PopMartData {
    val listPopMart: ArrayList<PopMart>
        get() {
            val list = arrayListOf<PopMart>()

            // 1. Data Labubu
            list.add(PopMart(
                "Labubu",
                "FLIP WITH ME Vinyl Plush Doll",
                1290000,
                "Tersedia",
                R.drawable.img_labubu_1
            ))
            list.add(PopMart(
                "Labubu",
                "Let's Checkmate Series - Vinyl Plush Doll Labubu Zimomo",
                1290000,
                "Tersedia",
                R.drawable.img_labubu_2
            ))

            // 2. Data Crybaby
            list.add(PopMart(
                "Crybaby",
                "WHY SO SERIOUS SERIES - CRYBABY Vinyl Plush Blister Pack Halloween",
                479000,
                "Tersedia",
                R.drawable.img_crybaby_1
            ))
            list.add(PopMart(
                "Crybaby",
                "Crying For Love Series - Vinyl Plush Hanging Card (Love You Cherry Much)",
                492000,
                "Tersedia",
                R.drawable.img_crybaby_2
            ))

            // 3. Data Hirono
            list.add(PopMart(
                "Hirono",
                "Little Hare Plush Doll Pendant Action Figure",
                492000,
                "Tersedia",
                R.drawable.img_hirono_1
            ))
            list.add(PopMart(
                "Hirono",
                "Living Wild - Fight for Joy Plush Doll Action Figure",
                449000,
                "Tersedia",
                R.drawable.img_hirono_2
            ))

            return list
        }
}