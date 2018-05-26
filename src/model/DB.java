package model;

import java.sql.*;

/*
 * Class DB
 * Berisi method untuk menghubungkan ke database, menjalankan query, dan mengambil hasil query
 */
public class DB {
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conn;

    public DB() throws Exception {

		/*
		Method DB
		Konstruktor: Melakukan koneksi ke MySQL dan basis data
		Menerima masukan berupa string alamat koneksi ke MySQL dan basis data
		*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmd_pbo?user=root&password=");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void createQuery(String query) throws Exception {

		/*
		Method createQuery
		Mengeksekusi query tanpa mengubah isi data
		Menerima masukan harga string Query
		*/

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query)) {
                rs = stmt.executeQuery(query);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void createUpdate(String query) throws Exception {

		/*
		Method createUpdate
		Mengeksekusi query yang mengubah isi data (update, insert, delete)
		Menerima masukan berupa string query
		*/

        try {
            stmt = conn.createStatement();

            int hasil = stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet getResult() {

		/*
		Method getResult
		Memberikan hasil query
		*/

        ResultSet temp = null;

        try {
            return rs;
        } catch (Exception e) {
            return temp;
        }
    }

    public void closeResult() throws SQLException {

		/*
		Method closeResult
		Menutup hubungan dari eksekusi query
		*/

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
                throw e;
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }

    public void closeConnection() {

		/*
		Method closeConnection
		Menutup hubungan dengan MySQL dan basis data
		*/

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            }
        }
    }
}