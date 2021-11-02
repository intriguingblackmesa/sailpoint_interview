package com.github.intriguingblackmesa.sailpointinterview;

import java.util.List;

/** Processor interface */
public interface Processor {

    /**
     * Process a batch of messages
     * @param <T> Content type of the message
     * @param batch A batch of messages to be processed.
     */
    <T> void process(List<Message<T>> batch);
}

