# 🎁 POP MART CATALOG


### *Collect. Display. Search.*


[![Kotlin](https://img.shields.io/badge/Kotlin-Android-orange?style=for-the-badge&logo=kotlin)](https://img.shields.io/badge/Kotlin-Android-orange?style=for-the-badge&logo=kotlin) [![Android Studio](https://img.shields.io/badge/Android%20Studio-Mobile%20App-3DDC84?style=for-the-badge&logo=androidstudio)](https://img.shields.io/badge/Android%20Studio-Mobile%20App-3DDC84?style=for-the-badge&logo=androidstudio) [![XML](https://img.shields.io/badge/XML-Layout-blue?style=for-the-badge)](https://img.shields.io/badge/XML-Layout-blue?style=for-the-badge) [![Status](https://img.shields.io/badge/Status-UAS%20Project-success?style=for-the-badge)](https://img.shields.io/badge/Status-UAS%20Project-success?style=for-the-badge)

---

## Identitas


| Keterangan | Data |
| --- | --- |
| Nama | **Meizaluna Aurelia Frakasa** |
| NIM | **42430045** |
| Mata Kuliah | **Pemrograman Seluler** |
| Topik | **Katalog Pop Mart** |
| Platform | **Android** |
| Bahasa | **Kotlin + XML** |

---

## Abstract


Aplikasi **Pop Mart Catalog** merupakan aplikasi katalog dan pencarian data Pop Mart berbasis Android. Aplikasi ini dikembangkan menggunakan **Kotlin**, **XML Layout**, dan penyimpanan data sederhana berbasis **ArrayList** tanpa database eksternal. Sistem menyediakan fitur daftar Pop Mart, detail Pop Mart, pencarian data menggunakan **Linear Search**, pengurutan data menggunakan **Sorting**, validasi input, penanganan error menggunakan **try-catch**, serta pencatatan aktivitas aplikasi melalui **Logcat** dengan tag NIM.

**Keywords:** Android, Kotlin, ArrayList, Linear Search, Sorting, Intent, RecyclerView, Logcat.

---

## I. Introduction


Proyek ini dibuat sebagai implementasi UAS mata kuliah **Pemrograman Seluler** dengan pendekatan Project-Based Learning. Tema yang dipilih adalah **Katalog Pop Mart** karena merupakan koleksi boneka vinyl yang populer dan cocok dikembangkan menjadi aplikasi katalog dengan desain yang menarik dan responsif.

Tujuan utama aplikasi ini adalah menampilkan data Pop Mart, menyediakan fitur pencarian, sorting, detail data, validasi form, serta dokumentasi aktivitas aplikasi melalui Logcat.

---

## II. System Design


Aplikasi terdiri dari dua halaman utama:

| Halaman | Fungsi |
| --- | --- |
| **MainActivity** | Menampilkan daftar Pop Mart, search, sort A-Z, sort Z-A |
| **DetailActivity** | Menampilkan detail Pop Mart beserta gambar, series, harga, dan status |

Struktur package utama:

```
com.example.popmartcatalog
├── MainActivity.kt
├── DetailActivity.kt
├── PopMart.kt
├── PopMartData.kt
└── ListPopMartAdapter.kt
```

---

## III. Implementation


### A. Data Model


Data Pop Mart disimpan dalam model `PopMart` dengan atribut utama seperti nama, series, harga, status, dan gambar.

```kotlin
@Parcelize
data class PopMart(
    val name: String,
    val series: String,
    val price: Int,
    val status: String,
    val image: Int
) : Parcelable
```

### B. ArrayList Dataset


Data katalog disimpan menggunakan `ArrayList` pada `PopMartData.kt`. Data ini digunakan sebagai sumber utama untuk RecyclerView, search, sorting, dan detail Pop Mart.

```kotlin
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
            
            // 2. Data Crybaby
            list.add(PopMart(
                "Crybaby",
                "WHY SO SERIOUS SERIES",
                479000,
                "Tersedia",
                R.drawable.img_crybaby_1
            ))
            
            // 3. Data Hirono
            list.add(PopMart(
                "Hirono",
                "Little Hare Plush Doll",
                492000,
                "Tersedia",
                R.drawable.img_hirono_1
            ))
            
            return list
        }
}
```

### C. Linear Search


Fitur pencarian dilakukan secara manual menggunakan Linear Search dengan cara memeriksa setiap data Pop Mart satu per satu berdasarkan keyword nama atau series Pop Mart.

```kotlin
private fun searchPopMart(query: String) {
    val filteredList = ArrayList<PopMart>()

    for (item in list) {
        if (item.name.contains(query, ignoreCase = true) ||
            item.series.contains(query, ignoreCase = true)) {
            filteredList.add(item)
        }
    }

    val listPopMartAdapter = ListPopMartAdapter(filteredList)
    rvPopMart.adapter = listPopMartAdapter
}
```

### D. Sorting


Pengurutan data A-Z dan Z-A menggunakan fungsi bawaan Kotlin `sortBy` dan `sortByDescending` berdasarkan nama Pop Mart.

```kotlin
// Sort A-Z
btnSortAZ.setOnClickListener {
    list.sortBy { it.name }
    showRecyclerList()
    Toast.makeText(this, "Berhasil diurutkan A-Z", Toast.LENGTH_SHORT).show()
}

// Sort Z-A
btnSortZA.setOnClickListener {
    list.sortByDescending { it.name }
    showRecyclerList()
    Toast.makeText(this, "Berhasil diurutkan Z-A", Toast.LENGTH_SHORT).show()
}
```

### E. Intent Navigation


Navigasi antar halaman menggunakan Intent:

- MainActivity → DetailActivity

Data Pop Mart dikirim ke DetailActivity menggunakan `putExtra()` dengan Parcelable.

```kotlin
holder.itemView.setOnClickListener {
    val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
    intentDetail.putExtra(DetailActivity.EXTRA_POPMART, listPopMart[holder.bindingAdapterPosition])
    holder.itemView.context.startActivity(intentDetail)
}
```

### F. Validation, Try-Catch, and Logcat


MainActivity menggunakan validasi `if-else` untuk mengecek input kosong dan validasi karakter menggunakan Regex. Error ditangani dengan `try-catch`. Aktivitas aplikasi dicatat menggunakan Logcat dengan tag:

```
42430045
```

```kotlin
btnSearch.setOnClickListener {
    val query = edtSearch.text.toString().trim()
    if (query.isEmpty()) {
        edtSearch.error = "Nama tidak boleh kosong!"
        showRecyclerList()
        return@setOnClickListener
    }

    val regexPengaman = "^[a-zA-Z0-9 ]+$".toRegex()
    if (!regexPengaman.matches(query)) {
        edtSearch.error = "Gunakan huruf dan angka saja!"
        Toast.makeText(this, "Karakter tidak valid!", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }

    try {
        android.util.Log.d("UAS_LOGCAT", "Pencarian Pop Mart dijalankan oleh Luna - NIM: 42430045")
        searchPopMart(query)
    } catch (e: Exception) {
        android.util.Log.e("UAS_LOGCAT", "Error pencarian: ${e.message}")
        Toast.makeText(this, "Terjadi kesalahan sistem saat mencari", Toast.LENGTH_SHORT).show()
    }
}
```

---

## IV. Module Compliance


| Modul | Implementasi |
| --- | --- |
| Modul 2 & 3 | UI rapi, pink theme, portrait dan landscape |
| Modul 4 & 5 | Intent antar halaman dan validasi input |
| Modul 6 | ArrayList dan Linear Search |
| Modul 7 | Sorting A-Z dan Z-A |
| Modul 9 | Try-catch dan Logcat dengan tag NIM |

---

## V. User Interface Documentation


### A. Main Screen

<table align="center">
  <tr>
    <td align="center"><b>Tampilan Portrait</b></td>
    <td align="center"><b>Tampilan Landscape</b></td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/85f05f2b-72ff-4d00-8912-af95d7a27512" width="180" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/017cd852-2a93-4e0d-a14d-d6d24a25788d" width="380" />
    </td>
  </tr>
  <tr>
    <td align="center"><i>Halaman Utama (Tegak)</i></td>
    <td align="center"><i>Halaman Utama (Mendatar)</i></td>
  </tr>
</table>

<table align="center">
  <tr>
    <td align="center"><b>Hasil Sorting A-Z</b></td>
    <td align="center"><b>Hasil Sorting Z-A</b></td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/9baee616-c544-4875-81ee-3c9bd1605fc0" width="180" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/741640d0-1955-4459-8b20-dafcf85ee29f" width="180" />
    </td>
  </tr>
  <tr>
    <td align="center"><i>Urutan: A ke Z</i></td>
    <td align="center"><i>Urutan: Z ke A</i></td>
  </tr>
</table>

Halaman utama menampilkan daftar Pop Mart dalam format card dengan RecyclerView, dilengkapi dengan fitur pencarian, sorting A-Z dan Z-A.

**Fitur:**
- Logo Pop Mart di bagian atas
- Search bar untuk mencari Pop Mart berdasarkan nama atau series
- Tombol Sort A-Z dan Sort Z-A
- RecyclerView menampilkan daftar Pop Mart dalam bentuk card
- Setiap card menampilkan gambar, nama, series, harga, dan status ketersediaan

### B. Detail Screen

<table align="center">
  <tr>
    <td align="center"><b>Fitur Pencarian</b></td>
    <td align="center"><b>Halaman Detail Item</b></td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/494be7cc-273c-47a5-aab8-336407703447" width="180" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/fe95d40d-d80a-4c1a-ad20-ead50b97b60a" width="180" />
    </td>
  </tr>
  <tr>
    <td align="center"><i>Hasil Pencarian: "Crybaby"</i></td>
    <td align="center"><i>Informasi Detail Karakter</i></td>
  </tr>
</table>


Halaman detail menampilkan informasi lengkap dari Pop Mart yang dipilih.

**Fitur:**
- Tombol back untuk kembali ke halaman utama
- Gambar Pop Mart besar
- Nama Pop Mart
- Series/koleksi
- Harga dalam format Rupiah
- Status ketersediaan dengan background dinamis

### C. Logcat Validation (Authenticity & Debugging)
Bagian ini menampilkan rekam jejak aktivitas sistem yang membuktikan fungsionalitas fitur dan orisinalitas pengembang. Logcat digunakan untuk memantau apakah alur program berjalan sesuai rencana, terutama saat menangani input dari pengguna.

<p align="center">
  <img src="https://github.com/user-attachments/assets/c603a312-454b-455f-9410-d18c54d01594" width="800" />
</p>


Analisis Rekaman Log:

Otentikasi Pengembang: Pesan log secara spesifik mencantumkan nama Luna dan NIM 42430045, yang membuktikan bahwa kode dijalankan langsung oleh pemilik proyek.

Filter Tag UAS_LOGCAT: Penggunaan tag khusus memudahkan proses debugging untuk memisahkan pesan aplikasi dari ribuan log sistem Android lainnya.

Keamanan Try-Catch: Logcat ini juga menjadi bukti bahwa blok try-catch berhasil melewati fase kritis saat melakukan pemrosesan data tanpa terjadi crash atau force close.
---

## VI. Testing Result


| No | Test Case | Expected Result | Status |
| --- | --- | --- | --- |
| 1 | Membuka aplikasi | MainActivity tampil dengan daftar Pop Mart | ✅ Passed |
| 2 | Menampilkan RecyclerView | Data Pop Mart muncul dalam card format | ✅ Passed |
| 3 | Search Pop Mart | Data terfilter sesuai keyword nama atau series | ✅ Passed |
| 4 | Sort A-Z | Data terurut dari A ke Z berdasarkan nama | ✅ Passed |
| 5 | Sort Z-A | Data terurut dari Z ke A berdasarkan nama | ✅ Passed |
| 6 | Klik item Pop Mart | DetailActivity terbuka dengan data yang tepat | ✅ Passed |
| 7 | Search kosong | Error validasi muncul | ✅ Passed |
| 8 | Search karakter khusus | Ditangani dengan validasi regex | ✅ Passed |
| 9 | Logcat | Aktivitas tercatat dengan tag NIM 42430045 | ✅ Passed |
| 10 | Landscape mode | Layout menyesuaikan layar landscape | ✅ Passed |
| 11 | Klik back di detail | Kembali ke MainActivity | ✅ Passed |

---

## VII. Project Workflow


Pengembangan dilakukan secara bertahap dengan fokus pada setiap modul pembelajaran.

| Tahap | Fokus |
| --- | --- |
| Tahap 1 | UI portrait dan landscape dengan RecyclerView |
| Tahap 2 | Intent navigation dan Parcelable |
| Tahap 3 | ArrayList dan Linear Search |
| Tahap 4 | Sorting A-Z dan Z-A |
| Tahap 5 | Validasi input, try-catch, dan Logcat |
| Tahap 6 | Bug fixing dan dokumentasi |

---

## VIII. How to Run


1. Clone atau download repository ini.
2. Buka project menggunakan Android Studio.
3. Pastikan proses **Gradle Sync** berhasil dan status perangkat (HP/Emulator) sudah terhubung.
4. Pilih perangkat fisik (disarankan) atau emulator Android.
5. Klik tombol **Run** atau tekan `Shift + F10`.
6. Untuk melihat bukti otentikasi di Logcat, gunakan filter tag:


```
42430045
```


---

## IX. Project Structure


```
PopMartCatalog/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/popmartcatalog/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── DetailActivity.kt
│   │   │   │   ├── PopMart.kt
│   │   │   │   ├── PopMartData.kt
│   │   │   │   └── ListPopMartAdapter.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_detail.xml
│   │   │   │   │   ├── item_popmart.xml
│   │   │   │   │   ├── activity_main.xml (landscape)
│   │   │   │   │   ├── activity_detail.xml (landscape)
│   │   │   │   │   └── item_popmart.xml (landscape)
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── img_labubu_1.webp
│   │   │   │   │   ├── img_crybaby_1.webp
│   │   │   │   │   ├── img_hirono_1.webp
│   │   │   │   │   ├── logo_popmart.png
│   │   │   │   │   └── bg_status_tersedia.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── mipmap/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── README.md
├── FIXES_APPLIED.md
├── COMPLETE_FIX_GUIDE.md
└── gradle.properties
```

---

## X. Key Features


### ✨ Core Features
- **Daftar Pop Mart**: Tampilkan semua Pop Mart dalam format card yang menarik
- **Pencarian**: Cari Pop Mart berdasarkan nama atau series menggunakan Linear Search
- **Pengurutan**: Urutkan Pop Mart A-Z atau Z-A
- **Detail**: Lihat informasi lengkap dari setiap Pop Mart
- **Validasi**: Validasi input pencarian dengan regex dan error handling
- **Logging**: Catat semua aktivitas aplikasi di Logcat

### 🎨 UI/UX Features
- **Responsive Design**: Layout yang baik di portrait dan landscape
- **Pink Theme**: Desain dengan color scheme pink yang sesuai dengan Pop Mart
- **Dark Mode Support**: Mendukung dark theme
- **Smooth Navigation**: Animasi transisi antar halaman
- **CardView**: Setiap item ditampilkan dalam format card yang elegan

---


## XI. Conclusion


Aplikasi **Pop Mart Catalog** berhasil dibuat sesuai ketentuan UAS Pemrograman Seluler. Aplikasi ini mengimplementasikan UI responsif dengan RecyclerView, Intent untuk navigasi, ArrayList untuk penyimpanan data, Linear Search untuk fitur pencarian, Sorting untuk pengurutan, validasi input dengan regex, try-catch untuk error handling, dan Logcat untuk dokumentasi aktivitas.

---

### ✨ Collect Your Favorites. Display with Pride.


**UAS Pemrograman Seluler — 2026**

---

## Quick Links


- 📱 [Android Documentation](https://developer.android.com/)
- 🎨 [Material Design](https://material.io/design)
- 🔗 [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- 📚 [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)
