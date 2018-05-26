package model;

import java.sql.SQLException;

/*
 * Class model untuk object pinjaman
 * Berisi query-query sql yang dijalankan dengan method milik class DB
 * yang merupakan class ortu dari Koleksi
 */
public class Koleksi extends DB {
    public Koleksi() throws Exception {
        super();
    }

    public void getAll() {
        // select * from tkoleksi
        try {
            String query = "SELECT * FROM tkoleksi";
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void get(String username) {
        // select * from tkoleksi where username = {username}
        try {
            String query = "SELECT * FROM tkoleksi where username = '" + username + "'";
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void insert(String username, String jeniskoleksi, int skorkoleksi, int skortotal) {
        // insert into tkoleksi
        try {
            String query = "INSERT INTO tkoleksi VALUES ('" + username + "','" + jeniskoleksi + "'," + skorkoleksi + "," + skortotal + ")";
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void update(String username, String jeniskoleksi, int skorkoleksi, int skortotal) {
        // update tkoleksi
        try {
            String query = "UPDATE tkoleksi SET jeniskoleksi = '" + jeniskoleksi + "', skorkoleksi = " + skorkoleksi + ", skortotal = " + skortotal + " WHERE username = '" + username + "';";
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void delete(String username) {
        // delete from tkoleksi
        try {
            String query = "DELETE FROM tkoleksi WHERE username = '" + username + "';";
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}