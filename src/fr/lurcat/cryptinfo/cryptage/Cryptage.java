package fr.lurcat.cryptinfo.cryptage;

import java.util.Arrays;
import java.util.List;

public class Cryptage {
	
	public static final List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	
	public static String translate(String msg, int key){
		
		if(key < 0)	key+=26;
		
		char[] lettres = msg.toLowerCase().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for(char let : lettres){
			if(ALPHABET.contains(let)){
				int i = ALPHABET.indexOf(let);
				i += key;
				int y = i%26;
				sb.append(ALPHABET.get(y));
			} else sb.append(let);
		}
		
		return sb.toString();
	}

}
