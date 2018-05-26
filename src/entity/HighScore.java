package entity;

/**
 * Class melambangkan satu baris data yang ada di tabel database dan tableview application
 * Berisi empat data/atribut beserta getset nya
 */
public class HighScore {
    private final String username;
    private final String jenisKoleksi;
    private final Integer skorKoleksi;
    private final Integer skorTotal;

    public HighScore(String username, String jenisKoleksi, Integer skorKoleksi, Integer skorTotal) {
        this.username = username;
        this.jenisKoleksi = jenisKoleksi;
        this.skorKoleksi = skorKoleksi;
        this.skorTotal = skorTotal;
    }

    public String getUsername() {
        return username;
    }

    public String getJenisKoleksi() {
        return jenisKoleksi;
    }

    public Integer getSkorKoleksi() {
        return skorKoleksi;
    }

    public Integer getSkorTotal() {
        return skorTotal;
    }
}
