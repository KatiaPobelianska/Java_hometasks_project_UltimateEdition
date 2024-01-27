package org.example.carengine.engines;

import org.springframework.stereotype.Component;

@Component("electric")
public class ElectricEngine implements Engine{
    @Override
    public String workEngine() {
        return "This works electric engine.";
    }
}
