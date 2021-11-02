# Sailpoint Interview Question Solution

The solution is to use a priority queue that automatically orders the messages as they come in (the number of the message corresponding to the priority). However, message batches will not be created until the number of the current head of the priority queue matches the expected number. Then, messages will be added to the batch until this is no longer true. When this happens, the batch will be sent off for processing.

The entry point to test the collating Listener is TestSolution.java. It reads from a test file (hamlet.txt) line-by-line, stores them in a list, scrambles the list, and then sends messages containing elements of the list one-by-one to the Listener. The Listener uses a Processor that writes each message from a batch to a file (new_hamlet.txt). The end result should be that hamlet.txt is identical to new_hamlet.txt, indicating that the messages were correctly ordered.
