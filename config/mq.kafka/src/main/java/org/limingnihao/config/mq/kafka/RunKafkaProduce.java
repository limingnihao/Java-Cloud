package org.limingnihao.config.mq.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class RunKafkaProduce {
    private final Producer<String, String> producer;
    public final static String TOPIC = "logstest";

    private RunKafkaProduce() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", "1");
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
    }

    void produce() {
        int messageNo = 1;
        final int COUNT = 101;

        int messageCount = 0;
        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "Hello kafka message :" + key;
            producer.send(new ProducerRecord<String, String>(TOPIC, key, data));
            System.out.println(data);
            messageNo++;
            messageCount++;
        }

        System.out.println("Producer端一共产生了" + messageCount + "条消息！");
    }

    public static void main(String[] args) {
        new RunKafkaProduce().produce();
    }

}
