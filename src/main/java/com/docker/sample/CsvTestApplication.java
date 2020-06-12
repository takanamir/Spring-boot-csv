package com.docker.sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvTestApplication {

	@SuppressWarnings("null")
	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(CsvTestApplication.class, args);
		String file = "CSVCSV.csv"; // 出力するCSVファイル名
		Connection cn = null; // DB接続に使うインターフェース
		PreparedStatement st = null; // SQL文でMySQLからデータを取ってきたものを代入する
		PrintWriter pw = new PrintWriter(file); // ファイルへの書き込み用クラス
		ResultSet rs = null; // SQLで持ってきた結果をセットする
		String path = "jdbc:mysql://localhost:3306/csv"; // 接続先
		String id = "root";
		String password = "root";
		String sql = "SELECT * FROM csv.csv_table";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(path, id, password);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				pw.print(rs.getString("id") + " ");
				pw.print(rs.getString("name") + "\n");
			}
			pw.close();
			System.out.println("Successed");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("failed");
		}
	}
}