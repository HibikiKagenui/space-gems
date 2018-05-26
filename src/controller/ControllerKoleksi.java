package controller;

import entity.HighScore;
import model.Koleksi;

import java.util.ArrayList;
import java.util.List;

/*
 * Class controller
 * Berisi method-method yang dapat dipanggil view untuk mendapatkan data dari model
 */
public class ControllerKoleksi {
    private List<HighScore> hasil;
    private String error;

    public ControllerKoleksi() {
        //konstruktor
        hasil = new ArrayList<>();
    }

    public void getAll() {
        // memasukkan hasil query getAll ke dalam array daftar
        try {
            Koleksi model = new Koleksi();
            model.getAll();

            while (model.getResult().next()) {
                hasil.add(new HighScore(
                        model.getResult().getString("username"),
                        model.getResult().getString("jeniskoleksi"),
                        model.getResult().getInt("skorkoleksi"),
                        model.getResult().getInt("skortotal")
                ));
            }
            model.closeResult();
            model.closeConnection();
        } catch (Exception e) {
            error = e.toString();
            System.out.println(error);
        }
    }

    public List<HighScore> getHasil() {
        // return list hasil
        return this.hasil;
    }

    public String getError() {
        // return pesan error terakhir
        return this.error;
    }

    public void get(String username) {
        try {
            Koleksi model = new Koleksi();
            model.get(username);
            while (model.getResult().next()) {
                hasil.add(new HighScore(
                        model.getResult().getString("username"),
                        model.getResult().getString("jeniskoleksi"),
                        model.getResult().getInt("skorkoleksi"),
                        model.getResult().getInt("skortotal")
                ));
            }
            model.closeResult();
            model.closeConnection();
        } catch (Exception e) {
            error = e.toString();
            System.out.println(error);
        }
    }

    /*fungsi untuk menambahkan data ke dalam database*/
    public void insert(HighScore arg) {
        // memasukkan argumen-argumen fungsi ke method insert dari model
        try {
            Koleksi model = new Koleksi();
            model.insert(arg.getUsername(), arg.getJenisKoleksi(), arg.getSkorKoleksi(), arg.getSkorTotal());
            model.closeResult();
            model.closeConnection();
        } catch (Exception e) {
            this.error = e.toString();
            System.out.println(this.error);
        }
    }

    public void update(HighScore arg) {
        // memasukkan argumen-argumen fungsi ke method update dari model
        try {
            Koleksi model = new Koleksi();
            model.update(arg.getUsername(), arg.getJenisKoleksi(), arg.getSkorKoleksi(), arg.getSkorTotal());
            model.closeResult();
            model.closeConnection();
        } catch (Exception e) {
            this.error = e.toString();
            System.out.println(this.error);
        }
    }

    public void delete(HighScore arg) {
        // memasukkan argumen-argumen fungsi ke method delete dari model
        try {
            Koleksi model = new Koleksi();
            model.delete(arg.getUsername());
            model.closeResult();
            model.closeConnection();
        } catch (Exception e) {
            this.error = e.toString();
            System.out.println(this.error);
        }
    }
}
