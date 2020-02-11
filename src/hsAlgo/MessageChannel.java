package hsAlgo;

import java.util.concurrent.locks.*;

public class MessageChannel {
    private final Lock msgLock = new ReentrantLock();
    private final Condition msgSent = msgLock.newCondition();
    private final Condition msgRead = msgLock.newCondition();
    Message message;

    public void SendMessage(Message msg) {
        // acquire a lock to write
        msgLock.lock();

        // writing the message to queue and signal the waiting thread to read the message
        try {
            while (this.message != null) {
                msgRead.await();
            }
            this.message = msg;
            msgSent.signalAll();
        } catch (Exception e) {
            System.out.println("Unable to send message. Exited with exception: "+e);
        } finally {
            msgLock.unlock();

        }
    }

    public Message ReceiveMessage() {
        // acquire lock to read the message content
        msgLock.lock();
        Message msg = new Message(0, "", 0);

        // reading the message and signal that waiting thread can write 
        try {
            while ( this.message == null) {
                msgSent.await();
            }
            msg = this.message;
            this.message = null;
            msgRead.signalAll();
        } catch (Exception e) {
            System.out.println("Unable to read message. Exited with exception: "+e);
        } finally {
            msgLock.unlock();
        }

        return msg;
    }
}
