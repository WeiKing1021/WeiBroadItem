package me.wei.broaditem.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.PluginCore;

public class IOManager {
	
	private static int buf_size = 1024;
	
	public static void load(IOInterface io) {
		try {
			// ���J�]�w��
			io.loadBroadItem();
			io.loadBroadFormat();
		}
		catch (IOException e) {
			Manager.log(e.toString(), Level.WARNING);
		}
		catch (SQLException e) {
			Manager.log(e.toString(), Level.WARNING);
		}		
	}
	
	public static void save(IOInterface io) {
		try {
			// ���J�]�w��
			io.saveBroadItem();
			io.saveBroadFormat();
		}
		catch (IOException e) {
			Manager.log(e.toString(), Level.WARNING);
		}
		catch (SQLException e) {
			Manager.log(e.toString(), Level.WARNING);
		}	
	}
	
	public static void copyDefault(String path) throws IOException {
		// �B�z���׽u�k�׽u���D
		path = path.replace("\\", "/");
		
		// ���]���s�b�N�qjar�����X���x�s
		InputStream Input = PluginCore.class.getResourceAsStream("/defaultConfig" + path);
		File target = new File(GlobalVar.folder + path);
		
		// �إߥؿ�
		if (!target.exists()) {
			new File(target.getParent()).mkdirs();
			target.createNewFile();
		}
		
		// ��X�y
		OutputStream Output = new FileOutputStream(target);
		
		// �w�İϪ���
		byte[] buf = new byte[buf_size];
		int len;
		
		// ���_Ū��
		while ( (len = Input.read(buf)) > 0) {
			Output.write(buf, 0, len);
		}
		
		// ����IO�y
		Input.close();
		Output.close();
		
		// �i�����w�g���qjar�������F�ɮ�
		Manager.log("�w�����ɮ�: " + path);
	};
	
	public static YamlConfiguration getYamlConfig(String path) {
		// �����o�ؿ�
		File file = new File(GlobalVar.folder + path);		
		
		// �^��
		return getYamlConfig(file);
	}
	
	public static YamlConfiguration getYamlConfig(File file) {	
		// ���]���s�b�N�qjar�����X���x�s
		if (!file.exists()) {
			
			String path = file.getAbsolutePath().replace(GlobalVar.folder, "");
			
			// ���սƻs
			try { copyDefault(path); }
			catch (IOException e) {
				Manager.log(path + " �L�k�x�s, ��]��: " + e.toString(), Level.WARNING);
				return null;
			}
		}
		
		// �^��
		return YamlConfiguration.loadConfiguration(file);
	}
}
