package hsAlgo;

public class Message {
    int uid;
    byte direction; // 0 - inbound ; 1 - outbound
    int hopCount;

    public Message(int uid, byte direction, int hopCount ) {
        this.uid = uid;
        this.direction = direction;
        this.hopCount = hopCount;
    }
}
