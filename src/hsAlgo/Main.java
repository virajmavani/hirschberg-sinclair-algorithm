package hsAlgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws IOException{
        //read the file
        // dummy ids = 3 5 2 7 9 1 4
        ArrayList<Integer> ids = new ArrayList<>();
        leaderElection master = new leaderElection();

        File file = new File("./test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s;

        while ((s = br.readLine()) != null) {
            int id = Integer.parseInt(s);
            ids.add(id);
        }

        master.Run( ids );
    }
}