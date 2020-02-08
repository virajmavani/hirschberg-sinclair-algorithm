package hsAlgo;

class MessageChannel {
    Message msg;
    boolean msg_sent;
    public MessageChannel() {
        this.msg_sent = false;
    }

    public void Send(Message msg) {
        this.msg = msg;
        this.msg_sent = true;
    }

    public Message Receive() {
        if (this.msg_sent) {
            this.msg_sent = false;
            return this.msg;
        }
        else {
            return new Message(0, "", 0);
        }
    }
}