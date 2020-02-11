package hsAlgo;

import java.lang.Thread;

class Process extends Thread {

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
        //thread acquires lock
        Globals.roundBegins.lock();

        if( this.status == -1 ) { //It is unknown / not yet defeated
            // start execution when receive signal from master to begin round
            Globals.roundStarts.await();

            //Begin the HS algorithm

            /* Message generation function */

            //message to predecessor
            Message msgPred = new Message(this.uid, 1, );

            //message to successor
            Message msgSucc = new Message(this.uid, 1, )

            this.send_to_pred.SendMessage(msgPred);
            this.send_to_succ.SendMessage(msgSucc);

            /* Transition Function */



        }


    }
}