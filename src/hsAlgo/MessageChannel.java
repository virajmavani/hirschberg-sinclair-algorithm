package hsAlgo;

import java.util.concurrent.locks.*;

public class MessageChannel {
    private final Lock msgLock = new ReentrantLock();
    private final Condition msgSent = msgLock.newCondition();

    public void SendMessage(Message msg) {
        //acquire a lock to write
        msgLock.lock();

        //writing the message to queue and signal the waiting thread to read the message
        try {

        } catch (Exception e) {
            System.out.println("Unable to send message. Exited with exception: "+e);
        } finally {
            msgLock.unlock();

        }
    }

    public void ReceiveMessage(Message msg) {

    }
}
