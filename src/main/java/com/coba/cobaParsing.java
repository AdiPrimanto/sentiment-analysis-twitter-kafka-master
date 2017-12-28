package com.coba;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class cobaParsing {
	public static void main(String[] args) throws FileNotFoundException {
		String dirPositif = "D:/MATERI/sentimen/positive.txt";
		String dirNegatif = "D:/MATERI/sentimen/negative.txt";
		Parsing parsing = new Parsing();
		
		ArrayList positif = parsing.parse(dirPositif);
		ArrayList negatif = parsing.parse(dirNegatif);
		
		int panjang = positif.size();
		
		for(int i = 0; i <panjang;i++) {
			System.out.println(positif.get(i));
		}
		
	}
}
