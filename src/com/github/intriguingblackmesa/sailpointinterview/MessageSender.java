package com.github.intriguingblackmesa.sailpointinterview;

import java.util.concurrent.BlockingQueue;

/** Message sending component that sends a message by adding it to a 
 * blocking queue
 */
public class MessageSender<T> {
    private final BlockingQueue<Message<T>> messageQueue;

    public MessageSender(BlockingQueue<Message<T>> messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * Sends a message by additing it to a blocking queue
     * @param message The message to send
     */
    public void sendMessage(Message<T> message) {
        messageQueue.offer(message);
    }
}