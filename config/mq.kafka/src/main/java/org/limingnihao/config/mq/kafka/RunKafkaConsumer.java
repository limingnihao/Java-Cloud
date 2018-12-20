package org.limingnihao.config.mq.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.*;

public class RunKafkaConsumer {

    private final Consumer consumer;

    private final static String TOPIC = "logstest";


    private RunKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", "1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
    }


    public void consume() {
        consumer.subscribe(Arrays.asList(TOPIC));
        // consumer.seekToBeginning(new ArrayList<>());

        // ===== 拿到所有的topic ===== //
        Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
        for(Map.Entry<String, List<PartitionInfo>> entries : listTopics.entrySet()){
            System.out.println(entries.getKey());
        }
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(2000);
            System.out.println("records=" + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("[fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value() + "]");
            }
        }
    }

    public static void main(String[] args) {
        new RunKafkaConsumer().consume();
    }

}

