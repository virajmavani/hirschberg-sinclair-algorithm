package hsAlgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class leaderElection {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Process> threads = new ArrayList<Process>();

        File file = new File("./test.txt"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
  
        String s;
        int n = 0;
        while ((s = br.readLine()) != null) {
            int id = Integer.parseInt(s);
            ids.add(id);
            n++;
        }
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
        
    }
}
