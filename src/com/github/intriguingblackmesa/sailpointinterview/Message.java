package com.github.intriguingblackmesa.sailpointinterview;

/** Message consisting of the number (i.e., order) of the message and 
 * generic content.
 */
public class Message<T> implements Comparable<Message<T>> {
    private int number;
    private T content;

    public Message() {

    }

    public Message(int number, T data) {
        this.number = number;
        this.content = data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T data) {
        this.content = data;
    }

    @Override
    public int compareTo(Message<T> message) {
        return this.number - message.number;
    }

    @Override
    public String toString() {
        return "Message " + number + ": " + content;
    }

    
}