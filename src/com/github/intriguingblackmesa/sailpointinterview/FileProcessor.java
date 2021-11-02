package com.github.intriguingblackmesa.sailpointinterview;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/** A FileProcessor that processes a batch of messages by writing them to a new file 
 * in the current directory. If that file current exists, it will be appended to.**/
public class FileProcessor implements Processor {

    @Override
    public <T> void process(List<Message<T>> batch) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("new_hamlet.txt", true))) {
            for (Message<T> message : batch) {
                writer.append(message.getContent().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    
}
