package org.example.carengine.engines;

import org.springframework.stereotype.Component;

@Component("gas")
public class GasEngine implements Engine{
    @Override
    public String workEngine() {
        return "This works gas engine.";
    }
}
