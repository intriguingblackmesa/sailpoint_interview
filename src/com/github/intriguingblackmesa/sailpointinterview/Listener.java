package com.github.intriguingblackmesa.sailpointinterview;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  A collating Listener that receives a single message, buffers it in a priority queue, 
 *  and from time to time submits a valid batch of buffered messages to the processor.
 *  The priority queue ensures that the messages are in the proper order before
 *  they are sent to the processor.
 */
public class Listener<T> {
    private final PriorityQueue<Message<T>> buffer;
    private final BlockingQueue<Message<T>> messageQueue;
    int currentMessageNumber;
    Processor processor;

    public Listener(int startingMessageNumber, 
                    PriorityQueue<Message<T>> buffer,
                    Processor processor,
                    BlockingQueue<Message<T>> messageQueue) {
        this.currentMessageNumber = startingMessageNumber;
        this.buffer = buffer;
        this.processor = processor;
        this.messageQueue = messageQueue;
    }

    /** Receives a message by taking a message off of the blocking queue.
     *  If the message at the top of the priority queue matches what is the
     *  expected currentMessageNumber, we begin to create a batch to send to the processor.
     *  Batch creation ends when we reach a Message in the priority queue that does have
     *  the expected currentMessageNumber, and the batch is then sent off to be processed.
     */
    public void receiveMessage() {
        try {
            Message<T> message = messageQueue.take();

            buffer.add(message);
            List<Message<T>> batch = new ArrayList<>();

            while (!buffer.isEmpty() && buffer.peek().getNumber() == currentMessageNumber) {
                batch.add(buffer.poll());
                currentMessageNumber++;
            }

            if (!batch.isEmpty()) {
                processor.process(batch);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
