package org.example.carengine.cars;

import org.example.carengine.engines.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private Engine engine;
@Autowired
    public Car(
            @Qualifier("diesel") Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine.workEngine() +
                '}';
    }
}
