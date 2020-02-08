package hsAlgo;

class Message {
    int id;
    String type;
    int hop_count;

    public Message(int id, String type, int hop_count) {
        this.id = id;
        this.type = type;
        this.hop_count = hop_count;
    }
}