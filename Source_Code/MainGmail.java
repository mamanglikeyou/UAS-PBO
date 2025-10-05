import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface untuk operasi dasar pengguna
interface UserOperations {
    void registrasiAkun();
    boolean loginAkun();
    void logoutAkun();
    String getUsername();
}

// Interface untuk operasi dasar email
interface EmailOperations {
    void tampilkan();
    int hitungJumlahEmail();
    List<String> getEmails();
    void tambahEmail(String email);
    void hapusEmail(int index);
    void kirimEmail(int index);
}

// Implementasi dari UserOperations (operasi dasar pengguna)
class User implements UserOperations {
    private String username, password;

    @Override
    public void registrasiAkun() {
        Scanner input = new Scanner(System.in);

        System.out.println("== DAFTAR AKUN ==");
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();

        System.out.println("Akun Anda berhasil didaftarkan.");
    }

    @Override
    public boolean loginAkun() {
        Scanner input = new Scanner(System.in);

        System.out.println("== LOGIN  ==");
        System.out.print("Username: ");
        String inputUsername = input.nextLine();
        System.out.print("Password: ");
        String inputPassword = input.nextLine();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("Login berhasil.");
            return true;
        } else {
            System.out.println("Username atau password salah.");
            return false;
        }
    }

    @Override
    public void logoutAkun() {
        System.out.println("Anda berhasil logout.");
    }

    @Override
    public String getUsername() {
        return username;
    }
}

// Implementasi dari EmailOperations
abstract class Email implements EmailOperations {
    protected List<String> emails;

    public Email() {
        emails = new ArrayList<>();
    }

    public String getEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            return emails.get(index);
        } else {
            return null;
        }
    }

    @Override
    public void tambahEmail(String email) {
        emails.add(email);
    }

    @Override
    public int hitungJumlahEmail() {
        return emails.size();
    }

    @Override
    public List<String> getEmails() {
        return emails;
    }

    @Override
    public void hapusEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            emails.remove(index);
        } else {
            System.out.println("Indeks email tidak valid.");
        }
    }
}

// Kelas turunan dari Email
class KotakMasuk extends Email {
    @Override
    public void tampilkan() {
        System.out.println("== Kotak Masuk ==");
        if (emails.isEmpty()) {
            System.out.println("Tidak ada email dalam kotak masuk.");
        } else {
            for (String email : emails) {
                System.out.println(email);
            }
        }
    }

    @Override
    public int hitungJumlahEmail() {
        int jumlahEmail = super.hitungJumlahEmail();
        System.out.println("Jumlah email dalam kotak masuk: " + jumlahEmail);
        return jumlahEmail;
    }

    @Override
    public void kirimEmail(int index) {
    }
}

// Kelas turunan dari Email
class Terkirim extends Email {
    private List<String> statusPengiriman;

    public Terkirim() {
        statusPengiriman = new ArrayList<>();
    }

    public void tambahStatusPengiriman(String status) {
        statusPengiriman.add(status);
    }

    @Override
    public void tampilkan() {
        System.out.println("== Kotak Terkirim ==");
        if (emails.isEmpty()) {
            System.out.println("Tidak ada email terkirim.");
        } else {
            for (int i = 0; i < emails.size(); i++) {
                System.out.println(emails.get(i));
                System.out.println("Status Pengiriman: " + statusPengiriman.get(i));
                System.out.println();
            }
        }
    }

    @Override
    public void hapusEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            super.hapusEmail(index);
            statusPengiriman.remove(index);
            System.out.println("Email berhasil dihapus dari Terkirim.");
        } else {
            System.out.println("Indeks email tidak valid.");
        }
    }

    @Override
    public void kirimEmail(int index) {
    }
}

// Kelas turunan dari Email
class Draf extends Email {
    @Override
    public void tampilkan() {
        System.out.println("== Draf ==");
        if (emails.isEmpty()) {
            System.out.println("Tidak ada email dalam draf.");
        } else {
            for (String email : emails) {
                System.out.println(email);
            }
        }
    }

    @Override
    public void hapusEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            super.hapusEmail(index);
            System.out.println("Email berhasil dihapus dari Draf.");
        } else {
            System.out.println("Indeks email tidak valid.");
        }
    }

    @Override
    public void kirimEmail(int index) {
        Scanner input = new Scanner(System.in);

        System.out.print("Apakah Anda yakin ingin mengirimkan email ini dari Draf? (y/n): ");
        String konfirmasi = input.next();

        if (konfirmasi.equalsIgnoreCase("y")) {
            // Mengirim email
            String email = getEmail(index);
            System.out.println("Email berhasil dikirim ke " + email.substring(email.indexOf("Tujuan: ") + 8, email.indexOf("\n", email.indexOf("Tujuan: "))));
            hapusEmail(index);
        } else {
            System.out.println("Pengiriman email dari Draf dibatalkan.");
        }
    }

    @Override
    public int hitungJumlahEmail() {
        int jumlahEmail = super.hitungJumlahEmail();
        System.out.println("Jumlah email dalam draf: " + jumlahEmail);
        return jumlahEmail;
    }
}

// Kelas turunan dari Email
class Buat extends Email {
    private String email;
    private String pengirim;
    private String tujuan;
    private String subjek;
    private String isi;

    public String getEmail() {
        return email;
    }

    @Override
    public void tampilkan() {
        System.out.println("== Buat Email Baru ==");
        Scanner input = new Scanner(System.in);
        System.out.print("Pengirim: ");
        pengirim = input.nextLine();
        System.out.print("Tujuan: ");
        tujuan = input.nextLine();
        System.out.print("Subjek: ");
        subjek = input.nextLine();
        System.out.print("Isi: ");
        isi = input.nextLine();

        email = "Pengirim: " + pengirim + "\n" +
                "Tujuan: " + tujuan + "\n" +
                "Subjek: " + subjek + "\n" +
                "Isi: " + isi + "\n";

        tambahEmail(email);
        System.out.println("Email berhasil dibuat.");
    }

    @Override
    public void kirimEmail(int index) {
        Scanner input = new Scanner(System.in);

        System.out.print("Apakah Anda yakin ingin mengirimkan email ini? (y/n): ");
        String konfirmasi = input.next();

        if (konfirmasi.equalsIgnoreCase("y")) {
            // Mengirim email
            String email = getEmail(index);
            System.out.println("Email berhasil dikirim ke " + email.substring(email.indexOf("Tujuan: ") + 8, email.indexOf("\n", email.indexOf("Tujuan: "))));
            hapusEmail(index);
        } else {
            // Simpan email ke dalam Draf
            System.out.println("Email disimpan dalam Draf.");
        }
    }
}

class HapusEmail {
    public static void hapusEmail(List<EmailOperations> daftarEmail) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nomor email yang ingin dihapus: ");
        int nomorEmail = input.nextInt();

        if (nomorEmail >= 1 && nomorEmail <= daftarEmail.size()) {
            EmailOperations email = daftarEmail.get(nomorEmail - 1);
            email.tampilkan();

            System.out.print("Apakah Anda yakin ingin menghapus email ini? (y/n): ");
            String konfirmasi = input.next();

            if (konfirmasi.equalsIgnoreCase("y")) {
                email.hapusEmail(nomorEmail - 1);
                System.out.println("Email berhasil dihapus.");
            } else {
                System.out.println("Penghapusan email dibatalkan.");
            }
        } else {
            System.out.println("Nomor email tidak valid.");
        }
    }
}

class PencarianEmail {
    public static void cariEmail(List<EmailOperations> daftarEmail, String keyword) {
        boolean ditemukan = false;
        for (EmailOperations email : daftarEmail) {
            for (String emailContent : email.getEmails()) {
                if (emailContent.contains(keyword)) {
                    System.out.println("== Hasil Pencarian ==");
                    email.tampilkan();
                    ditemukan = true;
                    break;
                }
            }
            if (ditemukan) {
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Email tidak ditemukan.");
        }
    }
}

class MainMenu {
    public static void tampilkan(UserOperations user) {
        int pilihanMenu;
        Scanner input = new Scanner(System.in);

        KotakMasuk kotakMasuk = new KotakMasuk();
        Terkirim terkirim = new Terkirim();
        Draf draf = new Draf();
        Buat buat = new Buat();
        List<EmailOperations> daftarEmail = new ArrayList<>();
        daftarEmail.add(kotakMasuk);
        daftarEmail.add(terkirim);
        daftarEmail.add(draf);

        do {
            System.out.println("\nSelamat Datang di Gmail, " + user.getUsername());
            System.out.println("----------------------\n");
            System.out.println("== Silahkan Memilih Menu ==");
            System.out.println("1. Kotak Masuk");
            System.out.println("2. Terkirim");
            System.out.println("3. Draf");
            System.out.println("4. Buat Email Baru");
            System.out.println("5. Cari Email");
            System.out.println("6. Hapus Email");
            System.out.println("7. Logout");
            System.out.println();
            System.out.print("Pilihan: ");
            pilihanMenu = input.nextInt();

            switch (pilihanMenu) {
                case 1:
                    kotakMasuk.tampilkan();
                    kotakMasuk.hitungJumlahEmail();
                    break;
                case 2:
                    terkirim.tampilkan();
                    terkirim.hitungJumlahEmail();
                    break;
                case 3:
                    draf.tampilkan();
                    draf.hitungJumlahEmail();
                    int nomorEmailDraf = draf.hitungJumlahEmail();
                    if (nomorEmailDraf > 0) {
                        System.out.print("Masukkan nomor email yang ingin dikirim: ");
                        int nomorEmail = input.nextInt();
                        if (nomorEmail >= 1 && nomorEmail <= nomorEmailDraf) {
                            EmailOperations email = draf;
                            email.kirimEmail(nomorEmail - 1);
                            terkirim.tambahEmail(draf.getEmail(nomorEmail - 1));
                            terkirim.tambahStatusPengiriman("Sudah Terkirim");
                            draf.hapusEmail(nomorEmail - 1);
                        } else {
                            System.out.println("Nomor email tidak valid.");
                        }
                    } else {
                        System.out.println("Tidak ada email dalam draf.");
                    }
                    break;
                case 4:
                    buat.tampilkan();
                    System.out.print("Apakah Anda ingin mengirim email ini? (y/n): ");
                    String konfirmasi = input.next();
                    if (konfirmasi.equalsIgnoreCase("y")) {
                        kotakMasuk.tambahEmail(((Buat) buat).getEmail());
                        ((Buat) buat).kirimEmail(buat.hitungJumlahEmail() - 1);
                        terkirim.tambahEmail(buat.getEmail());
                        terkirim.tambahStatusPengiriman("Sudah Terkirim");
                    } else {
                        draf.tambahEmail(((Buat) buat).getEmail());
                        System.out.println("Email berhasil disimpan di Draf.");
                    }
                    break;
                case 5:
                    input.nextLine();
                    System.out.print("Masukkan kata kunci: ");
                    String keyword = input.nextLine();
                    PencarianEmail.cariEmail(daftarEmail, keyword);
                    break;
                case 6:
                    HapusEmail.hapusEmail(daftarEmail);
                    break;
                case 7:
                    user.logoutAkun();
                    pilihanMenu = 0; // Set pilihanMenu ke 0 agar keluar dari perulangan utama
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (pilihanMenu != 0);
    }
}

public class MainGmail {
    public static void main(String[] args) {
        int pilihanMenu;
        Scanner input = new Scanner(System.in);

        User user = new User();

        do {
            System.out.println("\nSelamat Datang Di Gmail");
            System.out.println("----------------------\n");
            System.out.println("== Silahkan Memilih Menu ==");
            System.out.println("1. Daftar Akun");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.println();
            System.out.print("Pilihan: ");
            pilihanMenu = input.nextInt();

            switch (pilihanMenu) {
                case 1:
                    user.registrasiAkun();
                    break;
                case 2:
                    if (user.loginAkun()) {
                        MainMenu.tampilkan(user);
                    }
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan Gmail. Semoga Hari-Hari Anda Menyenangkan");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (pilihanMenu != 3);
    }
}