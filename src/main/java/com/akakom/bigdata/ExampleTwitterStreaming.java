package com.akakom.bigdata;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import twitter4j.FilterQuery;
import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class ExampleTwitterStreaming {
	public static void main(String[] args) {
		final String consumerKey = "ySZ08tjrUQ8srEle7qs2Jecli";
		final String consumerSecret = "W92NmGjU6gWmpAQJxWQgJK3FagACQRIqmRYqffsbeAvUOATZ8l";
		final String consumerToken = "380088951-QIMquBPGv6kqEUsTbNy9d4x7m1xjcoDUviLbIUSH";
		final String consumerTokenSecret = "woc8uQXJSr1trgtwj6VayTDhnN6zU0qX0pZJuxyvvbrbW";
		
		String[] keywords = {"jokowi", "stya", "novanto", "161217Akakom", "akakom"};
		
		FilterQuery qry = new FilterQuery();
		qry.track(keywords);
		
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthConsumerKey(consumerKey)
		.setOAuthConsumerSecret(consumerSecret)
		.setOAuthAccessToken(consumerToken)
		.setOAuthAccessTokenSecret(consumerTokenSecret);
		
		TwitterStream twitterstream = new TwitterStreamFactory(configurationBuilder.build())
				.getInstance();
		StatusListener listener = null;
		twitterstream.addListener(listener);
		
		twitterstream.addListener(new StatusListener() {
			
			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStatus(Status status) {
				JSONObject value = new JSONObject(status);
				SendKafka(value.toString());
				System.out.println("from -> "+status.getUser().getName());
				System.out.println("data -> "+status.getText()+" "+value);
//				System.out.println("Data -> "+status);
			}
			
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		twitterstream.filter(qry);
		
	}
	public static void SendKafka(String message) {
		 
        String BOOTSTRAP_SERVERS = "localhost:9092";
        String topic = "akakom";
 
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
 
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        try {
            producer.send(new ProducerRecord<String, String>(topic, message));
            producer.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
