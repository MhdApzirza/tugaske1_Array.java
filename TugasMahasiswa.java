// Nama : Mahardika Ahmad
// NIM  : 24106050077

import java.util.Scanner;

public class TugasMahasiswa {
    // Kapasitas array tetap 10 sesuai aturan
    static int kapasitas = 10;
    // Kolom 0 untuk Nama, Kolom 1 untuk NIM
    static String[][] dataMahasiswa = new String[kapasitas][2];
    // Variabel count untuk melacak jumlah data terisi
    static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n======= MENU DATA MAHASISWA =======");
            System.out.println("Data Terisi: " + count + " / " + kapasitas);
            System.out.println("-----------------------------------");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at Given Position");
            System.out.println("3. Insert at End");
            System.out.println("4. Delete from Beginning");
            System.out.println("5. Delete Given Position");
            System.out.println("6. Delete from End");
            System.out.println("7. Delete First Occurrence (By Nama)");
            System.out.println("8. Show Data");
            System.out.println("9. Exit");
            System.out.print("Pilih menu (1-9): ");
            pilihan = input.nextInt();
            input.nextLine(); // Membersihkan buffer enter

            switch (pilihan) {
                case 1:
                    insertAtPos(0, input);
                    break;
                case 2:
                    System.out.print("Masukkan posisi index (0-" + count + "): ");
                    int pos = input.nextInt();
                    input.nextLine();
                    insertAtPos(pos, input);
                    break;
                case 3:
                    insertAtPos(count, input);
                    break;
                case 4:
                    deleteAtPos(0);
                    break;
                case 5:
                    System.out.print("Masukkan posisi index yang dihapus: ");
                    int delPos = input.nextInt();
                    deleteAtPos(delPos);
                    break;
                case 6:
                    deleteAtPos(count - 1);
                    break;
                case 7:
                    System.out.print("Masukkan Nama yang ingin dihapus: ");
                    String namaCari = input.nextLine();
                    deleteFirstOccurrence(namaCari);
                    break;
                case 8:
                    showData();
                    break;
                case 9:
                    System.out.println("Program selesai. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 9);

        input.close();
    }

    // --- FUNGSI INSERT ---
    static void insertAtPos(int posisi, Scanner in) {
        if (count >= kapasitas) {
            System.out.println("Gagal: Kapasitas array sudah penuh!");
            return;
        }
        if (posisi < 0 || posisi > count) {
            System.out.println("Gagal: Posisi di luar jangkauan!");
            return;
        }

        // Geser data ke kanan untuk memberi ruang di 'posisi'
        for (int i = count; i > posisi; i--) {
            dataMahasiswa[i][0] = dataMahasiswa[i - 1][0];
            dataMahasiswa[i][1] = dataMahasiswa[i - 1][1];
        }

        System.out.print("Masukkan Nama: ");
        dataMahasiswa[posisi][0] = in.nextLine();
        System.out.print("Masukkan NIM : ");
        dataMahasiswa[posisi][1] = in.nextLine();

        count++; // NB 1: count ditambah 1
        System.out.println("Data berhasil ditambahkan di index " + posisi);
    }

    // --- FUNGSI DELETE BERDASARKAN POSISI ---
    static void deleteAtPos(int posisi) {
        if (count == 0) {
            System.out.println("Gagal: Data masih kosong!");
            return;
        }
        if (posisi < 0 || posisi >= count) {
            System.out.println("Gagal: Index tidak ditemukan!");
            return;
        }

        // Geser data ke kiri untuk menimpa data yang dihapus
        for (int i = posisi; i < count - 1; i++) {
            dataMahasiswa[i][0] = dataMahasiswa[i + 1][0];
            dataMahasiswa[i][1] = dataMahasiswa[i + 1][1];
        }

        // Bersihkan bekas data terakhir
        dataMahasiswa[count - 1][0] = null;
        dataMahasiswa[count - 1][1] = null;

        count--; // NB 2: count dikurang 1
        System.out.println("Data pada index " + posisi + " berhasil dihapus.");
    }

    // --- FUNGSI DELETE FIRST OCCURRENCE ---
    static void deleteFirstOccurrence(String target) {
        for (int i = 0; i < count; i++) {
            // Membandingkan teks tanpa peduli besar/kecil huruf
            if (dataMahasiswa[i][0].equalsIgnoreCase(target)) {
                deleteAtPos(i);
                return; // Berhenti setelah menemukan yang pertama
            }
        }
        System.out.println("Data dengan nama '" + target + "' tidak ditemukan.");
    }

    // --- FUNGSI TAMPILKAN DATA ---
    static void showData() {
        if (count == 0) {
            System.out.println("Array Kosong (Tidak ada data mahasiswa).");
        } else {
            System.out.println("\n--- LIST DATA MAHASISWA ---");
            for (int i = 0; i < count; i++) {
                System.out.println("Index [" + i + "] -> Nama: " + dataMahasiswa[i][0] + " | NIM: " + dataMahasiswa[i][1]);
            }
        }
    }
}