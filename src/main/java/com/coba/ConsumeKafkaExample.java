package com.coba;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.jfree.ui.RefineryUtilities;

import twitter4j.JSONException;
import twitter4j.JSONObject;

public class ConsumeKafkaExample {
	private static KafkaConsumer<String, String> consumer;
	static Test test = new Test();
	static int akumulasi = 0;
	
	public static int getAkumulasi() {
		return akumulasi;
	}
	
	public static void setAkumulasi(int x) {
		akumulasi = akumulasi+x;
	}
	
	
	public static void consume(){
//		public static void consume() throws JSONException {
		JFreeChart_ok demo = new JFreeChart_ok("Dynamic Line And TimeSeries Chart");
		demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");

		props.put("group.id", "group." + UUID.randomUUID().toString());
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "latest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(props);
		// Consume kafka multiple topic
		consumer.subscribe(Arrays.asList("cobacoba"));
		
		String hasil ="";
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				// System.out.println(record.value());
//				JSONObject json = new JSONObject(record.value().toString());
				//String ambil = new String();
				String ambil[] = {record.value().toString().toLowerCase()};
				//System.out.println("panjang array: "+ambil.length);
				//ambil[0] = ambil[0].replaceAll("[^a-z]", " ");
			    //System.out.println(ambil[0]);
				hasil = test.sentimen(ambil);
				if(hasil.equals("+")) {
					setAkumulasi(1);
				}else if(hasil.equals("-")) {
					setAkumulasi(-1);
				}
				System.out.println("HASIL " +hasil);
				System.out.println(getAkumulasi());
				
				//System.out.printf(json.getString("id") + "| " + json.getString("text") + "| ");
			}
		}
	}

}
