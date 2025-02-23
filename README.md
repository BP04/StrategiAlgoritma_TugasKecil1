# IQ Quizzler Pro
![Java 17](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)

IQ Puzzler Pro adalah permainan papan yang bertujuan dari permainan ini adalah pemain harus dapat mengisi seluruh papan dengan piece (blok puzzle) yang telah tersedia. Komponen penting dari permainan IQ Puzzler Pro terdiri dari:
1. Board (Papan) – Board merupakan komponen utama yang menjadi tujuan permainan dimana pemain harus mampu mengisi seluruh area papan menggunakan blok-blok yang telah disediakan. 
2. Blok/Piece – Blok adalah komponen yang digunakan pemain untuk mengisi papan kosong hingga terisi penuh. Setiap blok memiliki bentuk yang unik dan semua blok harus digunakan untuk menyelesaikan puzzle. Setiap blok dapat dirotasikan atau direfleksikan.

Tujuan dari program yang dibuat adalah untuk mencari 1 (satu) solusi dari masukan 
yang diberikan atau menyatakan bahwa tidak ada solusi.

## Instalasi
### 1. Linux
```
sudo apt install openjdk-17-jdk
```
### 2. Windows
Unduh Java 17 ke atas pada link berikut. https://www.oracle.com/id/java/technologies/downloads/

## Menjalankan Program

1. clone repository
```bash
git clone https://github.com/BP04/Tucil1_13523067.git
```

2. pindah ke directory src
```bash
cd src
```

3. Compile & Run
```bash
javac -d ../bin Main.java
java -cp ../bin Main
```

## Masukan dan Keluaran

### Masukan

Baris pertama berisi $3$ bilangan N, M, dan P yang masing-masing menyatakan jumlah baris papan/board, jumlah kolom papan/board, dan banyaknya blok/piece. Baris selanjutnya berisi suatu string yang menyatakan jenis papan. Jika jenis papan adalah CUSTOM, beberapa baris selanjutnya akan diikuti oleh bentuk papan.
Baris-baris selanjutnya akan berisi bentuk dari setiap puzzle.

```
N M P
BoardType
<Piece 1>
<Piece 2>
...
<Piece P>
```

### Keluaran

Keluaran akan ditampilkan pada konsol. Pengguna kemudian akan diberi opsi untuk menyimpan solusi (jika ada) dalam bentuk .txt dan .jpg.

## Contoh Penggunaan
Ketik `<nama_file>` dengan format .txt (misal `test8.txt`) setelah program dijalankan.

![image](https://github.com/user-attachments/assets/f170ff01-2ed3-4391-a2da-16abaa5e2555)

## Author
| Nama | NIM | Kelas |
|------|---|---|
| Benedict Presley | 13523067 | K02 |  
