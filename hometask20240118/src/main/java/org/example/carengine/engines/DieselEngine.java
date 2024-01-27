package org.example.carengine.engines;

import org.springframework.stereotype.Component;

@Component("diesel")
public class DieselEngine implements Engine{
    @Override
    public String workEngine() {
        return "This works diesel engine.";
    }
}
