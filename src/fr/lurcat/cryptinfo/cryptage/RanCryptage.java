package fr.lurcat.cryptinfo.cryptage;

import java.util.HashMap;
import java.util.Map;

public class RanCryptage {
	
	public static String translate(String msg, Map<Character, Character> key){
		char[] chars = msg.toLowerCase().toCharArray();
		StringBuilder trad = new StringBuilder();
		
		for(char c : chars){
			if(Cryptage.ALPHABET.contains(c)) trad.append(key.get(c));
			else trad.append(c);
		}
		
		return trad.toString();
	}
	
	public static String decode(String msg, Map<Character, Character> key){
		Map<Character, Character> newKey = new HashMap<>();
		for(char c : key.keySet()){
			newKey.put(key.get(c), c);
		}
		
		return translate(msg, newKey);
	}
	
}
