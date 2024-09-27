package com.sans.kafka_demo_app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerWithManualCommit {

	public static void main(String[] args) throws IOException {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
	     props.put("group.id", "group_2");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     String topic1[]={"numbers"};
	     String topic2="string";
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList(topic1));
	     final int minBatchSize = 200;
	     List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
	     FileWriter fileWriter =new FileWriter("C:/Users/goelsa/Desktop/numbers.txt",true);
	     try {
	    	 
	    	 while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records) {
	        	 buffer.add(record);
	             System.out.printf("offset = %d, key = %s, value = %s, partition =%s%n", record.offset(), record.key(), record.value(), record.partition());
	         	}
	         System.out.println("Buffer Size is: "+buffer.size());
	         if(buffer.size()>=minBatchSize)
	 	    {
	 	    	fileWriter.append(buffer.toString());
	 	    	consumer.commitSync();
	 	    	buffer.clear();
	 	    }
	   }
	    
	    	 
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }finally {
	    	 consumer.close();
	    	 fileWriter.close();
	     }
	     
	     
	}
	

}
