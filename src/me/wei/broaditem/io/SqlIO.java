package me.wei.broaditem.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings( {"unused"} )
public class SqlIO implements IOInterface {
	
	private Connection connection;
	
	private String sql_host;
	
	private int sql_port;
	
	private String sql_dataBase;
	
	private String sql_user;
	
	private String sql_password;
	
	public Connection getConnection() {
		// 用try/catch 連接至SQL伺服器並且回傳
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://" + this.sql_host
					+ ":" + this.sql_port + "/"
					+ this.sql_dataBase, this.sql_user, this.sql_password);
			return con;
		}
		catch (SQLException e) { return null; }
	}
	
	public SqlIO(String sql_host, int sql_port, String sql_dataBase, String sql_user, String sql_password) {
		// 位置
		this.sql_host = sql_host;
		
		// 端口
		this.sql_port = sql_port;
		
		// 數據庫
		this.sql_dataBase = sql_dataBase;
		
		// 使用者
		this.sql_user = sql_user;
		
		// 密碼
		this.sql_password = sql_password;
		
		// 設定連線
		this.connection = this.getConnection();
	}
	
	private void createTable(String table_name) throws SQLException {
	}

	@Override
	public void loadBroadItem() throws SQLException {
		
		// 取得連線階段
		Statement stat = this.connection.createStatement();
		String table_name = "broaditem";
		stat.execute("CREATE TABLE IF NOT EXISTS " + table_name + "(" + 
					 "serID BIGINT NOT NULL AUTO_INCREMENT," + 
					 "Key TINYTEXT," + 
					 "BroadType TINYTEXT," + 
					 "ID INT," + 
					 "Data INT," + 
					 "Display TINYTEXT" +
					 "FormatOverride TINYTEXT" +
					 "Lores TEXT" +
					 "Enchantments TINYTEXT" +
					 "PRIMARY KEY (serID) ) ENGINE = InnoDB DEFAULT CHARSET=utf8");
		
		// 對無表的情況初始化設定
		
		// 取得查找資料
		ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM broaditem");

		//result.updateCharacterStream(columnLabel, reader);
		
		do {
		}
		while ( result.next() );
	}

	@Override
	public void loadBroadFormat() throws SQLException {
		// 取得查找資料
		ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM 'broadformat'");
		
		do {
			
		}
		while ( result.next() );
	}

	@Override
	public void saveBroadItem() throws IOException, SQLException {
		// TODO 自動產生的方法 Stub
		
	}

	@Override
	public void saveBroadFormat() throws IOException, SQLException {
		// TODO 自動產生的方法 Stub
		
	}

}
