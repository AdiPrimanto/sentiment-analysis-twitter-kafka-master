package com.coba;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.coba.Parsing;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

public class Test {
	
	
	public String sentimen(String data[]) {
		
		Parsing parsing = new Parsing();
		
		// Create a new bayes classifier with string categories and string features.
		Classifier<String, String> bayes = new BayesClassifier<String, String>();

		// Two examples to learn from.
		//String[] positiveText = "saya suka hari yang cerah".split("\\s");
		//String[] negativeText = "saya benci hujan".split("\\s");
		String dirPositif = "sentimen/positive.txt";
		String dirNegatif = "sentimen/negative.txt";
		
		ArrayList positif = null;
		try {
			positif = parsing.parse(dirPositif);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList negatif = null;
		try {
			negatif = parsing.parse(dirNegatif);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Learn by classifying examples.
		// New categories can be added on the fly, when they are first used.
		// A classification consists of a category and a list of features
		// that resulted in the classification in that category.
		//bayes.learn("+", Arrays.asList(positiveText));
		//bayes.learn("-", Arrays.asList(negativeText));
		bayes.learn("+",positif);
		bayes.learn("-",negatif);
		
		// Here are two unknown sentences to classify.
		String teks = data[0];
		String[] unknownText1 = teks.split("\\s");
//		String[] unknownText2 = "jokowi ramah tapi sering marah".split("\\s");

		String hasil = bayes.classify(Arrays.asList(unknownText1)).getCategory();
		
		
		
		
//		System.out.println( // will output "negative"
//		    bayes.classify(Arrays.asList(unknownText2)).getCategory());

		// Get more detailed classification result.
		((BayesClassifier<String, String>) bayes).classifyDetailed(
		    Arrays.asList(unknownText1));

		// Change the memory capacity. New learned classifications (using
		// the learn method) are stored in a queue with the size given
		// here and used to classify unknown sentences.
		bayes.setMemoryCapacity(500);
		return hasil;
//		return (bayes.classify(Arrays.asList(unknownText1)).getCategory());
	}
}
