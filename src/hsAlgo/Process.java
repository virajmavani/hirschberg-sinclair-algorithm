package hsAlgo;

class Process extends Thread {
    int uid;
    MessageChannel send_to_pred;
    MessageChannel send_to_succ;
    MessageChannel recv_from_pred;
    MessageChannel recv_from_succ;
    boolean isLeader;
    
    public Process(int id, MessageChannel send_to_pred, MessageChannel send_to_succ, MessageChannel recv_from_pred, MessageChannel recv_from_succ) {
        this.uid = id;
        this.send_to_pred = send_to_pred;
        this.send_to_succ = send_to_succ;
        this.recv_from_pred = recv_from_pred;
        this.recv_from_succ = recv_from_succ;
        this.isLeader = false;
    }

    @Override
    public void run() {

    }
}