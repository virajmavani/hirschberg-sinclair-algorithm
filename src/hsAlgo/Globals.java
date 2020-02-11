package hsAlgo;

import java.util.concurrent.locks.*;

public class Globals {
    public static Lock roundBegins = new ReentrantLock();
    public static Condition roundStarts = roundBegins.newCondition();
    public static int roundNumber = 0;
    public Globals() {
    }
}