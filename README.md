# Look4GitHub

Look4GitHub adalah aplikasi desktop yang menggunakan bahasa Java.
Aplikasi ini dapat melakukan pencarian user GitHub dengan memanfaatkan:

### JSON
JSON merupakan singkatan dari JavaScrpit Object Notation, yaitu format pertukaran data yang dapat dibaca oleh manusia dan dapat digenerate oleh komputer. Pertukaran data ini dapat dilakukan antar bahasa pemrograman yang berbeda. Selain sebagai pertukaran data, JSON juga dapat digunakan sebagai media penyimpanan data, seperti MongoDB. 
JSON dapat menggunakan bentuk sebagai:
- *Object*, yaitu sepasang nama dan nilai yang tidak terurut. Biasanya diapit dengan kurung kurawal ({ }). Nama diikuti dengan titik dua (:) kemudian nilai, dan setiap pasangan nama dan nilai dipisahkan oleh koma (,).
- *Larik*, yaitu kumpulan nilai-nilai yang terurut. Biasanya diapit dengan kurung siku ([ ]). Setiap nilai dipisahkan oleh koma (,).
- *Nilai*, yaitu dapat berupa sebuah string dalam tanda kutip ganda (" "), atau angka, atau true atau false atau null, atau sebuah objek atau sebuah larik.
- *String*, yaitu kumpulan dari karakter Unicode, yang diappit oleh tanda kutip ganda (" "). Di dalam string dapat digunakan backslash escapes (\\) untuk membentuk karakter khusus.
- *Angka*, mirip dengan angka di C atau Java, kecuali format oktal dan heksadesimal tidak digunakan.
 
### REST
REST (REpresentational State Transfer) adalah arsitektur metode komunikasi yang sering diterapkan dalam pengembangan layanan berbasis web. Biasanya REST dijalankan melalui HTTP. Proses yang dilakukan adalah membaca laman web tertentu yanng memuat file XML atau JSON. Interaksi antara lien dan server difasilitasi dengan sejumlah operasional dan URIs (Universal Resource Identifiers), seperti GET, POST, PUT, DELETE dan sebagainya. Aplikasi Look4GitHub ini akan berperan sebagai klien saja yang melakukan request ke server dengan memanfaatkan REST ini.

### GitHub API
GitHub API merupakan Application Programming Interface yang disediakan oleh GitHub. API ini berupa sekumpulan perintah, fungsi, dan protokol yang dapat digunakan developer yang memanfaaatkan GitHub sebagai resource. Pada aplikasi Look4GitHub ini, Github akan menjadi server dalam konteks REST di atas, sehingga aplikasi ini dapat melakukan request data kepada GitHub dengan protokol-protokol yang sesuai dengan GitHub API yang telah disediakan. Unutk lebih lengkapnya tentang GitHub API dapat dilihat di https://developer.github.com/v3/
