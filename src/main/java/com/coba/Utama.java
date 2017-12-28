package com.coba;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

public class Utama {
	public static void main(String[] args) {
		String ambil[] = {"@jokowi:Anak alay jaman sekarang/now."};
		String[] tanda = {"@",":","/","&","#","_","-","+","?","!","$","%","*","(",")","^","~","/."};
		
//		int i,panjangKata;
//		panjangKata = tanda.length;
//		System.out.println(panjangKata);
//		for(i=0;i<panjangKata;i++) {
//			if(ambil[0].contains(tanda[i].toString())) {
//				System.out.println("perulangan ke: "+i);
//				System.out.println(tanda[i].toString());
//				ambil[0]=ambil[0].replaceAll(tanda[i].toString(), " ");
//				System.out.println(ambil[0]);
//			}
//		}
		
		ambil[0] = ambil[0].replaceAll("[^a-zA-Z0-9]", " ");
	    System.out.println(ambil[0]);
		
		//System.out.println(ambil[0]);
		
	}
}
