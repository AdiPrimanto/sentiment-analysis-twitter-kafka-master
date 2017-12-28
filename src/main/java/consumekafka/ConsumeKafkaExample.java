package consumekafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class ConsumeKafkaExample {
	private static KafkaConsumer<String, String> consumer;

	public static void main(String[] args) throws JSONException {

		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.1.9:9092");

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
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				// System.out.println(record.value());
				JSONObject json = new JSONObject(record.value().toString());
				
//				sentimen(json.getString("text"));
				
				
				System.out.printf(json.getString("id") + "| " + json.getString("text") + "| ");
			}
		}
	}

}
