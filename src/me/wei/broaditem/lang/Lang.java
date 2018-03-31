package me.wei.broaditem.lang;

import java.util.HashMap;

public class Lang {
	private static HashMap<String, String> mapLang = new HashMap<>();
	
	public static void setLang(String key, String mes) {
		mapLang.put(key, mes);
	}
	
	public static String getLang(String key) {
		// ¨ú±o¦r¦ê
		String lang = mapLang.get(key);
		
		return (lang != null ? lang : "");
	}
	
	public static void reset() {
		mapLang.clear();
	}
}
