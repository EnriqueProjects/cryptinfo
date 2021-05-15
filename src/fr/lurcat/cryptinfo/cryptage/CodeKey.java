package fr.lurcat.cryptinfo.cryptage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeKey {
	
	@SuppressWarnings("resource")
	public static void CreateFile(Map<Character, Character> key, String path){
		File f = new File(path);
		StringBuilder sb = new StringBuilder();
		for(Character c : key.keySet()){
			String str = c + "6" + key.get(c) + "5";
			sb.append(str);
		}
		
		try { 
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		for(char c : sb.toString().toCharArray()){
			fos.write(c);
			
		}} catch (IOException e) {e.printStackTrace();}
	}
	
	@SuppressWarnings("resource")
	public static Map<Character, Character> readFile(String path){
		File f = new File(path);
		StringBuilder buffer = new StringBuilder();
		
		try{
			FileInputStream fis = new FileInputStream(f);
			int n = 0;
			while((n = fis.read()) >= 0){
				buffer.append((char) n);
			}
		} catch(Exception e){ e.printStackTrace(); }
		
		Map<Character, Character> map = new HashMap<>();
		
		String[] parse = buffer.toString().split("5");
		for(String seq : parse){
			String[] parse2 = seq.split("6");
			char[] tab1 = parse2[0].toCharArray();
			char[] tab2 = parse2[1].toCharArray();
			map.put(tab1[0], tab2[0]);			
		}
		
		return map;
	}
	
	public static Map<Character, Character> getDefaultKey(){
		Map<Character, Character> map = new HashMap<>();
		for(char c : Cryptage.ALPHABET){
			map.put(c, c);
		}
		return map;
	}

}
