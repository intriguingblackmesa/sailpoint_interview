package com.github.intriguingblackmesa.sailpointinterview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This tests the collating Listener by creating one thread that sends messages
 * consisting of out-of-order lines from a text file containg Hamlet, and
 * another thread that calls a Listener's receiveMessage() in a never-ending
 * loop.
 */
public class TestSolution {
    public static void main(String[] args) {
        LinkedBlockingQueue<Message<String>> messageQueue = new LinkedBlockingQueue<>();
        Listener<String> listener = new Listener<>(0, new PriorityQueue<>(), new FileProcessor(), messageQueue);
        MessageSender<String> sender = new MessageSender<>(messageQueue);

        ArrayList<Message<String>> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input_data/hamlet.txt"));) {
            String line = br.readLine();
            while (line != null) {
                list.add(new Message<>(list.size(), line + "\n"));
                line = br.readLine();
            }

            Collections.shuffle(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(() -> list.forEach(el -> sender.sendMessage(el)));

        executor.execute(() -> {
            while (true) {
                listener.receiveMessage();
            }
        });

        executor.shutdown();
    }
}
