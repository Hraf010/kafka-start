import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer {
    public static void main(String[] args) {

       // 1er : definir les properietes du consommateur :
        Properties properties = new Properties();
        // ou se trouve le broker
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG , "test-group-1");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,30000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //2eme : creer un consommateur
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        // subscribe à un topic
        kafkaConsumer.subscribe(Collections.singletonList("test2"));

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            System.out.println("*****************");
            // chaque second on fait un poll des messagesz qui ont ete envoyer dans les derniers 100millSeconds
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            consumerRecords.forEach(cr->{
                System.out.println("Key = "+cr.key()+"=>"+cr.value());
            });
        },100,1000, TimeUnit.MILLISECONDS);

    }
}
