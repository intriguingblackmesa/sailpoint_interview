package com.github.intriguingblackmesa.sailpointinterview;

import java.util.List;

/** A SimpleProcessor that processes a batch of messages by printing them to System.out */
public class SimpleProcessor implements Processor {

    @Override
    public <T> void process(List<Message<T>> batch) {
        for (Message<T> message : batch) {
            System.out.println(message);
        }
        
    }
    
}
