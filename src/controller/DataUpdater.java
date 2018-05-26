package controller;

import entity.HighScore;
import entity.gem.Gem;
import global.Constant;

import java.util.Collections;
import java.util.HashMap;

/**
 * Class runnable yang berfungsi memasukkan data permainan ke database setelah gamover
 * atau ketika pemain memilih kembali ke menu utama saat ditengah-tengah game
 */
public class DataUpdater implements Runnable {
    // atribut-atribut ini diisi value dari view gameplayview ketika class ini dibuatkan instance nya
    private HashMap<String, Integer> gemCounters;
    private String username;
    private Integer newScore;

    public DataUpdater(HashMap<String, Integer> gemCounters, String username, Integer newScore) {
        this.gemCounters = gemCounters;
        this.username = username;
        this.newScore = newScore;
    }

    /**
     * What the thread does
     */
    @Override
    public void run() {
        // menentukan jenisKoleksi mana yang memiliki nilai tertinggi yang pernah didapatkan di permainan terkahir
        String jenisKoleksi = "";
        for (String x : Gem.types) {
            if (gemCounters.get(x) > 0) {
                // bila counter tipe batu di atas 0 (pernah didapatkan di permainan terakhir)
                // ambil tipe batu dan hentikan looping
                jenisKoleksi = x;
                break;
            }
        }

        // menentukan skorKoleksi yang sesuai dengan jenisKoleksi
        int skorKoleksi = 0;
        switch (jenisKoleksi) {
            case "Yellow Diamond":
                skorKoleksi = 100;
                break;
            case "Zircon":
                skorKoleksi = 75;
                break;
            case "Jade":
                skorKoleksi = 60;
                break;
            case "Rutile":
                skorKoleksi = 40;
                break;
            case "Phosphophyllite":
                skorKoleksi = 35;
                break;
        }

        // buat instance controller database dan lakukan get by username
        ControllerKoleksi controller = new ControllerKoleksi();
        controller.get(username);
        if (controller.getHasil().size() > 0) {
            // bila username sudah ada di database

            // ambil data lama
            HighScore old = controller.getHasil().get(0);
            String oldJenisKoleksi = old.getJenisKoleksi();
            Integer oldSkorKoleksi = old.getSkorKoleksi();
            Integer oldSkorTotal = old.getSkorTotal();

            // bandingkan data lama dengan data baru
            if (skorKoleksi < oldSkorKoleksi) {
                // bila data lama lebih besar daripada data baru
                jenisKoleksi = oldJenisKoleksi;
                skorKoleksi = oldSkorKoleksi;
            }

            // update record dengan menambahkan skortotal dengan skor permainan terakhir
            HighScore baru = new HighScore(username, jenisKoleksi, skorKoleksi, oldSkorTotal + newScore);
            controller.update(baru);
        } else {
            // bila username baru, insert data
            HighScore baru = new HighScore(username, jenisKoleksi, skorKoleksi, newScore);
            controller.insert(baru);
        }
        // THREAD SLEEP //
        try {
            Thread.sleep(Constant.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
