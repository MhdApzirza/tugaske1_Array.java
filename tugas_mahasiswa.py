# Inisialisasi kapasitas 10 dengan nilai None (sebagai pengganti null di Java)
kapasitas = 10
data_mahasiswa = [[None, None] for _ in range(kapasitas)]
count = 0

def show_data():
    if count == 0:
        print("\nArray Kosong (Tidak ada data mahasiswa).")
    else:
        print("\n--- LIST DATA MAHASISWA ---")
        for i in range(count):
            print(f"Index [{i}] -> Nama: {data_mahasiswa[i][0]} | NIM: {data_mahasiswa[i][1]}")

def insert_at_pos(posisi):
    global count
    if count >= kapasitas:
        print("Gagal: Kapasitas penuh!")
        return
    if posisi < 0 or posisi > count:
        print("Gagal: Posisi tidak valid!")
        return

    # Input data
    nama = input("Masukkan Nama: ")
    nim = input("Masukkan NIM : ")

    # Shifting (Geser ke kanan)
    # Di Python kita bisa pakai slice atau loop mundur
    for i in range(count, posisi, -1):
        data_mahasiswa[i] = data_mahasiswa[i-1]

    data_mahasiswa[posisi] = [nama, nim]
    count += 1
    print(f"Data berhasil ditambahkan di index {posisi}")

def delete_at_pos(posisi):
    global count
    if count == 0:
        print("Gagal: Data kosong!")
        return
    if posisi < 0 or posisi >= count:
        print("Gagal: Index tidak ditemukan!")
        return

    # Shifting (Geser ke kiri)
    for i in range(posisi, count - 1):
        data_mahasiswa[i] = data_mahasiswa[i+1]
    
    data_mahasiswa[count-1] = [None, None]
    count -= 1
    print(f"Data di index {posisi} berhasil dihapus.")

def delete_first_occurrence(target):
    for i in range(count):
        if data_mahasiswa[i][0].lower() == target.lower():
            delete_at_pos(i)
            return
    print(f"Data dengan nama '{target}' tidak ditemukan.")

# Main Program
while True:
    print("\n======= MENU DATA MAHASISWA (PYTHON) =======")
    print(f"Data Terisi: {count} / {kapasitas}")
    print("--------------------------------------------")
    print("1. Insert at Beginning")
    print("2. Insert at Given Position")
    print("3. Insert at End")
    print("4. Delete from Beginning")
    print("5. Delete Given Position")
    print("6. Delete from End")
    print("7. Delete First Occurrence")
    print("8. Show Data")
    print("9. Exit")
    
    pilihan = input("Pilih menu (1-9): ")

    if pilihan == '1':
        insert_at_pos(0)
    elif pilihan == '2':
        pos = int(input(f"Masukkan posisi index (0-{count}): "))
        insert_at_pos(pos)
    elif pilihan == '3':
        insert_at_pos(count)
    elif pilihan == '4':
        delete_at_pos(0)
    elif pilihan == '5':
        pos = int(input("Masukkan index yang dihapus: "))
        delete_at_pos(pos)
    elif pilihan == '6':
        delete_at_pos(count - 1)
    elif pilihan == '7':
        target = input("Masukkan nama yang dihapus: ")
        delete_first_occurrence(target)
    elif pilihan == '8':
        show_data()
    elif pilihan == '9':
        print("Program Selesai Yey!")
        break
    else:
        print("Pilihan tidak valid!")