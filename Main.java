import java.util.ArrayList;
import java.util.Scanner;

// 1. Kelas Induk (Superclass)
class Mahasiswa {
    protected String nim;
    protected String nama;
    protected double nilai;

    public Mahasiswa(String nim, String nama, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }
}

// 2. Kelas Anak (Subclass) - Menerapkan Inheritance
class HitungNilai extends Mahasiswa {
    private String grade;
    private String status;

    public HitungNilai(String nim, String nama, double nilai) {
        super(nim, nama, nilai); // Memanggil constructor kelas induk
        hitungGradeDanStatus();
    }

    private void hitungGradeDanStatus() {
        if (nilai >= 80 && nilai <= 100) {
            this.grade = "A";
            this.status = "lulus";
        } else if (nilai >= 70 && nilai < 80) {
            this.grade = "B";
            this.status = "lulus";
        } else if (nilai >= 60 && nilai < 70) {
            this.grade = "C";
            this.status = "lulus";
        } else if (nilai >= 50 && nilai < 60) {
            this.grade = "D";
            this.status = "tidak lulus";
        } else if (nilai >= 0 && nilai < 50) {
            this.grade = "E";
            this.status = "tidak lulus";
        }
    }

    public String getGrade() { return grade; }
    public String getStatus() { return status; }
    
    public void tampilkanData() {
        System.out.println("NIM : " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("Nilai : " + (int)nilai);
        System.out.println("Grade: " + grade);
        System.out.println("=========================================");
    }
}

// 3. Kelas Utama (Main Class)
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<HitungNilai> daftarMahasiswa = new ArrayList<>();
        
        System.out.print("Masukkan jumlah mahasiswa yang ingin diinput: ");
        int jumlahData = input.nextInt();
        input.nextLine(); // Membersihkan buffer

        // Proses Input Data
        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\n--- Input Data Mahasiswa ke-" + (i + 1) + " ---");
            System.out.print("NIM  : ");
            String nim = input.nextLine();
            System.out.print("Nama : ");
            String nama = input.nextLine();
            
            double nilai;
            while (true) {
                System.out.print("Nilai: ");
                nilai = input.nextDouble();
                input.nextLine(); // Membersihkan buffer
                
                if (nilai >= 0 && nilai <= 100) {
                    break; // Nilai valid
                } else {
                    System.out.println("Input nilai anda salah (Harus 0-100). Silakan ulangi.");
                }
            }
            
            // Membuat objek dari subclass dan memasukkannya ke dalam list
            daftarMahasiswa.add(new HitungNilai(nim, nama, nilai));
        }

        System.out.println("\n\n=== OUTPUT DATA MAHASISWA ===");
        System.out.println("=========================================");
        
        // Variabel untuk kalkulasi rangkuman ringkasan/output bawah
        int totalMhs = daftarMahasiswa.size();
        int lulusCount = 0, tidakLulusCount = 0;
        int gradeACount = 0, gradeBCount = 0, gradeCCount = 0, gradeDCount = 0, gradeECount = 0;
        double totalNilai = 0;

        ArrayList<String> namaLulus = new ArrayList<>();
        ArrayList<String> namaTidakLulus = new ArrayList<>();
        ArrayList<String> namaA = new ArrayList<>();
        ArrayList<String> namaB = new ArrayList<>();
        ArrayList<String> namaC = new ArrayList<>();
        ArrayList<String> namaD = new ArrayList<>();
        ArrayList<String> namaE = new ArrayList<>();
        StringBuilder rumusRataRata = new StringBuilder();

        // Proses Menampilkan Data Satuan & Mengumpulkan Data Statistik
        for (int i = 0; i < totalMhs; i++) {
            HitungNilai mhs = daftarMahasiswa.get(i);
            mhs.tampilkanData(); // Panggil method cetak per mahasiswa

            totalNilai += mhs.nilai;
            rumusRataRata.append((int)mhs.nilai);
            if (i < totalMhs - 1) rumusRataRata.append("+");

            // Cek Status Kelulusan
            if (mhs.getStatus().equals("lulus")) {
                lulusCount++;
                namaLulus.add(mhs.nama);
            } else {
                tidakLulusCount++;
                namaTidakLulus.add(mhs.nama);
            }

            // Cek Grade
            switch (mhs.getGrade()) {
                case "A" -> { gradeACount++; namaA.add(mhs.nama); }
                case "B" -> { gradeBCount++; namaB.add(mhs.nama); }
                case "C" -> { gradeCCount++; namaC.add(mhs.nama); }
                case "D" -> { gradeDCount++; namaD.add(mhs.nama); }
                case "E" -> { gradeECount++; namaE.add(mhs.nama); }
            }
        }

        // Tampilan Rangkuman Output Bagian Bawah
        double rataRata = totalNilai / totalMhs;

        System.out.println("Jumlah Mahasiswa : " + totalMhs);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulusCount + " yaitu " + String.join(", ", namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulusCount + " yaitu " + String.join(", ", namaTidakLulus));
        
        if (gradeACount > 0) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeACount + " yaitu " + String.join(", ", namaA));
        if (gradeBCount > 0) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeBCount + " yaitu " + String.join(", ", namaB));
        if (gradeCCount > 0) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeCCount + " yaitu " + String.join(", ", namaC));
        if (gradeDCount > 0) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeDCount + " yaitu " + String.join(", ", namaD));
        if (gradeECount > 0) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeECount + " yaitu " + String.join(", ", namaE));
        
        System.out.printf("Rata-rata nilai mahasiswa adalah : %s / %d = %.2f\n", rumusRataRata.toString(), totalMhs, rataRata);
    }
}