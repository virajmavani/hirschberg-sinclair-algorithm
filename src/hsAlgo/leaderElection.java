package hsAlgo;

import java.util.concurrent.locks.*;
import java.util.ArrayList;

public class leaderElection{
    public void Run(ArrayList<Integer> processIds) {
        //master spawns new threads
        ArrayList<Process> pool = new ArrayList<Process>();
        int n = processIds.size();

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
            Process p = new Process(processIds.get(i), send_to_pred, send_to_succ, recv_from_pred, recv_from_succ);
            send_to_pred = send_to_succ;
            p.start();
            pool.add(p);
        }


        while(  ) { //condition to check whether round
            Globals.phaseBegins.lock(); //lock acquired
            
            Globals.phaseNumber++; //round number incremented

            System.out.println("The round number," + Globals.phaseNumber + "starts");

            Globals.phaseStarts.signalAll(); //all threads signalled

            while(Globals.endedProcesses != n);

            Globals.endedProcesses = 0;
        }
    }
}
