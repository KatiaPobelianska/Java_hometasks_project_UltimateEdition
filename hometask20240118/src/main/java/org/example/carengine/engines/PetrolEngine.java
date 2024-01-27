package org.example.carengine.engines;

import org.springframework.stereotype.Component;

@Component("petrol")
public class PetrolEngine implements Engine{
    @Override
    public String workEngine() {
        return "This works petrol engine.";
    }
}
