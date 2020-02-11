package hsAlgo;

import java.lang.Thread;

public class Process extends Thread {
    //constructor to initialize threads/processes
    int uid;
    MessageChannel send_to_pred;
    MessageChannel send_to_succ;
    MessageChannel recv_from_pred;
    MessageChannel recv_from_succ;
    int status; // -1 = Unknown; 1 - leader; 0 - non leader

    public Process(int id, MessageChannel send_to_pred, MessageChannel send_to_succ, MessageChannel recv_from_pred, MessageChannel recv_from_succ) {
        this.uid = id;
        this.send_to_pred = send_to_pred;
        this.send_to_succ = send_to_succ;
        this.recv_from_pred = recv_from_pred;
        this.recv_from_succ = recv_from_succ;
        this.status = -1;
    }

    @Override
    public void run() {
        // start execution when receive signal from master to begin round

    }
}