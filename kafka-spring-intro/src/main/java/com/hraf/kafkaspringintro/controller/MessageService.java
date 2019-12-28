package com.hraf.kafkaspringintro.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hraf.kafkaspringintro.entities.PizzaOrder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    // Creeyit hna 3 consumers bach nchoof it9assmo charge (load balancing)
    @KafkaListener(topics = "pizzashop" , groupId = "group1",clientIdPrefix = "client1")
    public void onMessageClient1(ConsumerRecord<String,String> record) throws Exception {
        PizzaOrder pizzaOrder= pizzaOrder(record.value());
            System.out.println("[Client 1 ]" +pizzaOrder.getName() + "=> " + pizzaOrder.getPrice());
    }

    @KafkaListener(topics = "pizzashop" , groupId = "group1",clientIdPrefix = "client2")
    public void onMessageClient2(ConsumerRecord<String,String> record) throws Exception {
        PizzaOrder pizzaOrder= pizzaOrder(record.value());
        System.out.println("[Client 2 ]" +pizzaOrder.getName() + "=> " + pizzaOrder.getPrice());
    }

    @KafkaListener(topics = "pizzashop" , groupId = "group1",clientIdPrefix = "client3")
    public void onMessageClient3(ConsumerRecord<String,String> record) throws Exception {
        PizzaOrder pizzaOrder= pizzaOrder(record.value());
        System.out.println("[Client 3 ]" +pizzaOrder.getName() + "=> " + pizzaOrder.getPrice());
    }

    private PizzaOrder pizzaOrder(String jsonPizzaOrder) throws Exception
    {
        JsonMapper jsonMapper = new JsonMapper();
        PizzaOrder pizzaOrder = jsonMapper.readValue(jsonPizzaOrder,PizzaOrder.class);
        return pizzaOrder;
    }

}
