package com.sans.kafka_demo_app;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumerWithPartitionAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
	     props.put("group.id", "group_3");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     String topic1="numbers";
	     String topic2="string";
	     TopicPartition partitions[] = {new TopicPartition(topic1, 2)};
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     
	     consumer.assign(Arrays.asList(partitions));
	     
	     
	     try {
	    	 
	    	 while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s, partition =%s%n", record.offset(), record.key(), record.value(), record.partition());
	     }
	    	 
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }finally {
	    	 consumer.close();
	     }
	}
}
