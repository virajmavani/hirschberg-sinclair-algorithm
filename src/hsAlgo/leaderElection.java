package hsAlgo;

import java.util.concurrent.locks.*;

public class leaderElection{
    public void Run(int[] processIds) {
        //master spawns new threads
        ArrayyList<Process> threads = new ArrayList<Process>();
        int n = processIds.length;

        Lock roundBegin = new RentrantLock(); // roundBegin is the lock and roundStarts in the condition that all processes wait on
        Condition roundStarts = roundBegin.newCondition();

        MessageChannel first_send = new MessageChannel();
        MessageChannel first_receive = new MessageChannel();
        MessageChannel send_to_pred = first_send;
        MessageChannel recv_from_pred = first_receive;
        for (int i = 0; i < n; i++) {
            MessageChannel send_to_succ;
            MessageChannel recv_from_succ;
            if (i == n - 1) {
                send_to_succ = first_send;
                recv_from_succ = first_receive;
            } else {
                send_to_succ = new MessageChannel();
                recv_from_succ = new MessageChannel();
            }
            Process p = new Process(ids.get(i), send_to_pred, send_to_succ, recv_from_pred, recv_from_succ);
            send_to_pred = send_to_succ;
            p.start();
            threads.add(p);
        }

        while( ) { //condition to check whether round
            roundStarts.signalAll();


    }
}
