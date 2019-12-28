package com.hraf.kafkaspringintro.metier;

import com.hraf.kafkaspringintro.entities.PizzaOrder;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    public PizzaOrder getPizzaOrderByName(String name){
        return new PizzaOrder(name,30.0);
    }
}
