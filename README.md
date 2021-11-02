The entry point to test the collating Listener is TestSolution.java.

The solution is to use a priority queue that automatically orders the messages as they come in (the number of the message corresponding to the priority). However, message batches will not be created until the number of the current head of the priority queue matches the expected number. Then, messages will be added to the batch until this is no longer true. When this happens, the batch will be sent off for processing.
