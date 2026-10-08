# Aplikasi Eksplorasi Digimon

Aplikasi mobile berbasis Android yang dikembangkan untuk mengeksplorasi data Digimon secara interaktif menggunakan Jetpack Compose, Material 3, arsitektur MVVM, dan Digi-API (DAPI).

---

## Screenshot Aplikasi

| Home Screen (Daftar & Pencarian) | Detail Screen (Informasi Lengkap) |
| :---: | :---: |
| ![Home Screen](docs/screenshots/home_screen.png) | ![Detail Screen](docs/screenshots/detail_screen.png) |

---

## Latar Belakang & Permasalahan

Dunia digital sedang dilanda kekacauan akibat ulah tamer jahat yang mengendalikan Digimon. Untuk mengembalikan kedamaian dunia digital, tamer perlu menganalisis jenis, level, tipe, dan atribut dari setiap Digimon yang dihadapi. Aplikasi ini hadir sebagai perangkat digital (Digivice) untuk membantu eksplorasi dan identifikasi data Digimon secara cepat dan akurat.

---

## Fitur Utama

1. **Home Screen (Daftar Digimon)**
   - Menampilkan koleksi Digimon dalam tata letak kartu responsif menggunakan `LazyVerticalGrid`.
   - Menampilkan identitas ID, nama, dan gambar Digimon via Coil.
   - Pencarian Digimon interaktif berdasarkan nama.
   - Penanganan status UI lengkap: *Loading*, *Success (Data)*, *Error* (dengan tombol Coba Lagi), dan *Empty State*.

2. **Digimon Detail Screen (Detail Informasi)**
   - Menampilkan 4 data utama yang diwajibkan:
     - **Nama Digimon**
     - **Level** (Child, Adult, Perfect, Ultimate, dll.)
     - **Attribute** (Vaccine, Data, Virus, Free)
     - **Type** (Reptile, Dragon, Holy Beast, dll.)
   - Menampilkan gambar beresolusi tinggi dengan indikator X-Antibody.
   - Menampilkan deskripsi resmi dalam bahasa Inggris.
   - Menampilkan daftar keahlian / jurus khusus (*Skills*).
   - Tombol navigasi kembali ke Home Screen.

---

## Arsitektur & Struktur Kode (MVVM)

Aplikasi dibangun dengan arsitektur **Model-View-ViewModel (MVVM)** dan *Repository Pattern*:

```text
com.example.exploredigimon/
├── data/
│   ├── model/
│   │   ├── DigimonDetailResponse.kt
│   │   ├── DigimonItem.kt
│   │   └── DigimonListResponse.kt
│   ├── network/
│   │   ├── DigiApiService.kt
│   │   └── RetrofitClient.kt
│   └── repository/
│       └── DigimonRepository.kt
├── ui/
│   ├── common/
│   │   └── UiState.kt
│   ├── navigation/
│   │   ├── NavGraph.kt
│   │   └── Screen.kt
│   ├── screens/
│   │   ├── detail/
│   │   │   ├── DetailScreen.kt
│   │   │   └── DetailViewModel.kt
│   │   └── home/
│   │       ├── HomeScreen.kt
│   │       └── HomeViewModel.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── MainActivity.kt
```

### 1. Model
- Merepresentasikan entitas data dari Digi-API menggunakan Kotlin `data class`.
- Menerapkan *Null safety* dengan nilai default untuk mencegah kesalahan parsing data yang tidak lengkap.

### 2. Repository
- `DigimonRepository`: Mengisolasi sumber data dari UI dan ViewModel, memanggil `DigiApiService` secara asinkron menggunakan Coroutines.

### 3. ViewModel
- `HomeViewModel` dan `DetailViewModel` mengelola UI state menggunakan `StateFlow`.
- Memisahkan logika bisnis dari tampilan dan menjaga status data saat terjadi perubahan konfigurasi.

### 4. View (Jetpack Compose)
- UI dibangun 100% secara deklaratif tanpa XML layout.
- Menggunakan Material Design 3 dengan `ColorScheme` bernuansa digital cyber dan `Typography` kustom.
- Pengelolaan state UI berbasis `UiState` (`Loading`, `Success`, `Error`).

---

## Persyaratan Teknis yang Terpenuhi

- **Bahasa**: Kotlin (Data class, Null safety, Lambda expression, Collection).
- **User Interface**: Jetpack Compose, Material Design 3, Custom Theme, Custom Typography.
- **List & Data**: `LazyVerticalGrid` dengan konsumsi data dari Digi-API.
- **Networking**: Retrofit 2 + Gson Converter mengonsumsi REST API resmi `https://digi-api.com/`.
- **Informasi Minimal**: Nama Digimon, Level, Attribute, Type (ditambah Gambar, Deskripsi, dan Skill).
- **Arsitektur**: MVVM (Model, View, ViewModel, Repository).
- **Navigasi**: Navigation Compose (2 screens: Home Screen dan Detail Screen).
- **UI State**: `Loading`, `Success (Data)`, `Error`.
- **Ketentuan**: Tanpa layout XML, tanpa database lokal, tanpa library tambahan yang tidak perlu, dan seluruh berkas kode dibuat bersih tanpa komentar kode.

---

## Cara Menjalankan Aplikasi

1. Clone repositori ini:
   ```bash
   git clone <url-repository-anda>
   cd responsi-pemmob
   ```
2. Buka proyek pada Android Studio (Ladybug / Koala / Flamingo atau versi terbaru).
3. Pastikan perangkat atau emulator terhubung dan memiliki akses internet.
4. Lakukan sinkronisasi Gradle (*Sync Project with Gradle Files*).
5. Klik **Run** atau jalankan perintah Gradle berikut:
   ```bash
   ./gradlew installDebug
   ```
