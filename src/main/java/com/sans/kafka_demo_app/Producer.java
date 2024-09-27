package com.sans.kafka_demo_app;


import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String clientId="my-producer";
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("acks", "all");
		props.put("client.id",clientId);
		int numOfRecords=100;
		String topic1="numbers";
		String topic2="string";
		KafkaProducer<String,String> producer=new KafkaProducer<>(props);

		try {
			
			
			  for(int i=1;i<numOfRecords;i++) {
			  System.out.println("Message No. "+i+" was just produced!"); producer.send(new
			  ProducerRecord<>(topic1,Integer.toString(i),Integer.toString(i))); }
			 
			
			/*
			 * for(int i=1;i<=numOfRecords;i++) {
			 * 
			 * String message=String.format("Producer %s has sent message %s at %s",
			 * clientId,i,new Date()); //String s=sc.nextLine();
			 * System.out.println("Message No. "+i+" was just produced!"); producer.send(new
			 * ProducerRecord<>(topic2,Integer.toString(i),message)); Thread.sleep(300); }
			 */
		producer.send(new ProducerRecord<>("numbers","End of message stream !!!!!"));
		producer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}

