package com.hraf.kafkaspringintro.controller;

import com.hraf.kafkaspringintro.entities.PizzaOrder;
import com.hraf.kafkaspringintro.metier.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class PizzaController {

    @Autowired
    private KafkaTemplate<String,PizzaOrder> kafkaTemplate;
    @Autowired
    private PizzaService pizzaService;
    @GetMapping("/orders/{name}")
    public String submitAnOrder(@PathVariable("name") String name){
        IntStream.range(0, 50)
                .forEach(i -> this.kafkaTemplate.send("pizzashop", String.valueOf(i),
                        new PizzaOrder("name"+i, (int)(Math.random()*100)))
                );

    return "ok!";
    }
}
