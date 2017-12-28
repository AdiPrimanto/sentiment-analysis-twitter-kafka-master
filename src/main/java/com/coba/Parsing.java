package com.coba;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parsing {
	String line;
	private Scanner sc;
	public ArrayList<String> parse(String fileName) throws FileNotFoundException{
		sc = new Scanner(new File(fileName));
		ArrayList<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		return lines;
	}
}

