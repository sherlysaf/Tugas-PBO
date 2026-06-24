/**
 * Tugas Pemrograman Berorientasi Objek
 * Topik: Polimorfisme (Method Overloading & Method Overriding)
 * Universitas Nusa Putra
 */

// Class Induk: Bank
class Bank {
    protected String namaBank = "Bank Standar";

    // 1.a.1) Method Overloading - Variasi 1: Transfer sesama bank
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("[" + namaBank + "] Transfer sebesar Rp" + jumlah + " ke rekening " + rekeningTujuan + " (Sesama Bank) berhasil.");
    }

    // 1.a.2) Method Overloading - Variasi 2: Transfer ke bank berbeda
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biayaTransfer = hitungBiayaTransfer(bankTujuan);
        System.out.println("[" + namaBank + "] Transfer sebesar Rp" + jumlah + " ke rekening " + rekeningTujuan + " di Bank " + bankTujuan + " berhasil.");
        System.out.println("      Biaya Transfer: Rp" + biayaTransfer + " | Total Debet: Rp" + (jumlah + biayaTransfer));
    }

    // 1.a.3) Method Overloading - Variasi 3: Transfer dengan tambahan berita
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biayaTransfer = hitungBiayaTransfer(bankTujuan);
        System.out.println("[" + namaBank + "] Transfer sebesar Rp" + jumlah + " ke rekening " + rekeningTujuan + " di Bank " + bankTujuan + " dengan berita '" + berita + "' berhasil.");
        System.out.println("      Biaya Transfer: Rp" + biayaTransfer + " | Total Debet: Rp" + (jumlah + biayaTransfer));
    }

    // 1.a.4) Method Overloading - Variasi 4: Suku Bunga Standar
    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // Bonus Challenge: Fitur menghitung biaya transfer berdasarkan bank tujuan
    public int hitungBiayaTransfer(String bankTujuan) {
        // Jika nama bank tujuan sama dengan bank pengirim, biaya Rp 0. Jika berbeda, dikenakan biaya Rp 6.500
        if (this.namaBank.equalsIgnoreCase(bankTujuan)) {
            return 0;
        } else {
            return 6500; // Biaya antar bank standar
        }
    }
}

// 2. Method Overriding - Subclass BankBNI
class BankBNI extends Bank {
    
    public BankBNI() {
        this.namaBank = "BNI";
    }

    // 2.a. Overriding Suku Bunga BNI menjadi 4%
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%");
    }

    // 2.c. Overriding method transferUang dengan 3 parameter
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Sesuai instruksi: bank tujuan otomatis bernilai sesuai nama bank ("BNI")
        String bankEfektif = "BNI";
        int biayaTransfer = hitungBiayaTransfer(bankEfektif);
        System.out.println("[BankBNI - Override] Memproses transaksi internal otomatis:");
        System.out.println("      Transfer sebesar Rp" + jumlah + " ke rekening " + rekeningTujuan + " di Bank " + bankEfektif + " berhasil.");
        System.out.println("      Biaya Transfer: Rp" + biayaTransfer);
    }
}

// 2. Method Overriding - Subclass BankBCA
class BankBCA extends Bank {

    public BankBCA() {
        this.namaBank = "BCA";
    }

    // 2.b. Overriding Suku Bunga BCA menjadi 4.5%
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    // 2.c. Overriding method transferUang dengan 3 parameter
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Sesuai instruksi: bank tujuan otomatis bernilai sesuai nama bank ("BCA")
        String bankEfektif = "BCA";
        int biayaTransfer = hitungBiayaTransfer(bankEfektif);
        System.out.println("[BankBCA - Override] Memproses transaksi internal otomatis:");
        System.out.println("      Transfer sebesar Rp" + jumlah + " ke rekening " + rekeningTujuan + " di Bank " + bankEfektif + " berhasil.");
        System.out.println("      Biaya Transfer: Rp" + biayaTransfer);
    }
}

// 1.c. Kelas utama untuk menguji metode dengan berbagai parameter
public class TugasPolimorfisme {
    public static void main(String[] args) {
        System.out.println("=== PENGUJIAN 1: METHOD OVERLOADING (Class Bank) ===");
        Bank bankUmum = new Bank();
        
        // Pengujian variasi 1
        System.out.println("\n> Menguji transferUang() Variasi 1 (Sesama Bank):");
        bankUmum.transferUang(500000, "1234567890");

        // Pengujian variasi 2
        System.out.println("\n> Menguji transferUang() Variasi 2 (Antar Bank):");
        bankUmum.transferUang(1000000, "9876543210", "Mandiri");

        // Pengujian variasi 3
        System.out.println("\n> Menguji transferUang() Variasi 3 (Dengan Berita):");
        bankUmum.transferUang(250000, "5554443322", "BRI", "Bayar Patungan Makan Malam");

        // Pengujian variasi 4
        System.out.println("\n> Menguji sukuBunga() Standar:");
        bankUmum.sukuBunga();


        System.out.println("\n\n=== PENGUJIAN 2: METHOD OVERRIDING (Subclass BankBNI & BankBCA) ===");
        
        // Instansiasi Subclass (Polimorfisme)
        Bank bni = new BankBNI();
        Bank bca = new BankBCA();

        // Menguji Overriding Suku Bunga
        System.out.println("> Menguji Suku Bunga Masing-Masing Bank:");
        bni.sukuBunga(); // Output: 4%
        bca.sukuBunga(); // Output: 4.5%

        // Menguji Overriding Method Transfer Uang (3 Parameter)
        System.out.println("\n> Menguji Overriding transferUang() pada BankBNI:");
        bni.transferUang(750000, "1122334455", "Mandiri"); 

        System.out.println("\n> Menguji Overriding transferUang() pada BankBCA:");
        bca.transferUang(1500000, "9988776655", "BNI");
        
        System.out.println("\n\n=== PENGUJIAN BONUS CHALLENGE: BIAYA TRANSFER ===");
        System.out.println("> Menguji transfer dari BCA ke sesama BCA menggunakan Variasi 3 (Gratis):");
        bca.transferUang(200000, "9988776655", "BCA", "Transfer sesama");
    }
}