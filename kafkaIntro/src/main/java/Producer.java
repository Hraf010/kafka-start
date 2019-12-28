import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) {
        int cmp = 0;
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"client-producer-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            String key = String.valueOf(new Random().nextInt(1000));
            String value = String.valueOf(new Random().nextDouble()*99999);
            kafkaProducer.send(new ProducerRecord<String, String>("test2",key,value),(metadata,ex)->{
                System.out.println("Sending msg => " + value+"=> "+metadata.partition());
            });
        },1000,100,TimeUnit.MILLISECONDS);
    }
}
