import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SIMULASI DATA AKADEMIK ===");

        // Input Mahasiswa
        System.out.print("Nama Mahasiswa: ");
        String mhsName = input.nextLine();
        System.out.print("Alamat Mahasiswa: ");
        String mhsAddress = input.nextLine();
        Student mhs = new Student(mhsName, mhsAddress);

        System.out.print("Input 1 Matkul Mahasiswa: ");
        String matkul = input.nextLine();
        System.out.print("Nilai: ");
        int nilai = input.nextInt();
        mhs.addCourseGrade(matkul, nilai);

        // Cetak Output
        System.out.println("\n=== HASIL ===");
        System.out.println(mhs.toString());
        mhs.printGrades();

        input.close();
    }
}