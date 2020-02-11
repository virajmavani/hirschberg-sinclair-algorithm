package hsAlgo;

import java.lang.Thread;
import java.lang.Math;

class Process extends Thread {

    int uid;
    MessageChannel send_to_pred;
    MessageChannel send_to_succ;
    MessageChannel recv_from_pred;
    MessageChannel recv_from_succ;
    int status; // -1 = Unknown; 1 - leader; 0 - non leader

    public Process(int id, MessageChannel send_to_pred, MessageChannel send_to_succ, MessageChannel recv_from_pred,
            MessageChannel recv_from_succ) {
        this.uid = id;
        this.send_to_pred = send_to_pred;
        this.send_to_succ = send_to_succ;
        this.recv_from_pred = recv_from_pred;
        this.recv_from_succ = recv_from_succ;
        this.status = -1;
    }

    @Override
    public void run() {
        while (true) {
            // thread acquires lock
            Globals.phaseBegins.lock();

            // start execution when receive signal from master to begin round
            try {
                Globals.phaseStarts.await();

                // Begin the HS algorithm

                if (this.status == -1) { // It is unknown / not yet defeated
                    /* Message generation function */

                    // message to predecessor
                    Message msgPred = new Message(this.uid, "out", (int) Math.pow(2, Globals.phaseNumber));

                    // message to successor
                    Message msgSucc = new Message(this.uid, "out", (int) Math.pow(2, Globals.phaseNumber));

                    this.send_to_pred.SendMessage(msgPred);
                    this.send_to_succ.SendMessage(msgSucc);
                }

                // Leader declaration
                if (this.status == 1) {
                    Message declaration = new Message(this.uid, "declaration", 0);
                    send_to_pred.SendMessage(declaration);
                    send_to_succ.SendMessage(declaration);
                }

                /* Transition Function */

                Message msg_from_pred = recv_from_pred.ReceiveMessage();

                if (msg_from_pred.direction == "out") {
                    if (msg_from_pred.uid > this.uid && msg_from_pred.hopCount > 1) {
                        msg_from_pred.hopCount--;
                        send_to_succ.SendMessage(msg_from_pred);
                        this.status = 0;
                    } else if (msg_from_pred.uid > this.uid && msg_from_pred.hopCount == 1) {
                        Message msg = new Message(msg_from_pred.uid, "in", 0);
                        send_to_pred.SendMessage(msg);
                        this.status = 0;
                    } else if (msg_from_pred.uid == this.uid) {
                        this.status = 1;
                    }
                }

                Message msg_from_succ = recv_from_succ.ReceiveMessage();
                if (msg_from_succ.direction == "out") {
                    if (msg_from_succ.uid > this.uid && msg_from_succ.hopCount > 1) {
                        msg_from_succ.hopCount--;
                        send_to_pred.SendMessage(msg_from_succ);
                        this.status = 0;
                    } else if (msg_from_succ.uid > this.uid && msg_from_succ.hopCount == 1) {
                        Message msg = new Message(msg_from_succ.uid, "in", 0);
                        send_to_succ.SendMessage(msg);
                        this.status = 0;
                    } else if (msg_from_succ.uid == this.uid) {
                        this.status = 1;
                    }
                }

                msg_from_pred = recv_from_pred.ReceiveMessage();

                if (msg_from_pred.direction == "in" && msg_from_pred.uid > this.uid) {
                    send_to_succ.SendMessage(msg_from_pred);
                }

                msg_from_succ = recv_from_succ.ReceiveMessage();

                if (msg_from_succ.direction == "in" && msg_from_succ.uid > this.uid) {
                    send_to_pred.SendMessage(msg_from_succ);
                }


            } catch (Exception e) {
                System.out.println("Process exited with exception: " + e);
            } finally {
                try {
                    Globals.mutex.acquire();
                    Globals.endedProcesses++;
                } catch (InterruptedException e) {
                    // Exception raised
                    e.printStackTrace();
                } finally {
                    Globals.mutex.release();
                }
                Globals.phaseBegins.unlock();
            }
        }
    }
}