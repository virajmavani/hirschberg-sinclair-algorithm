package hsAlgo;

import java.util.concurrent.locks.*;

public class Globals {
    public static Lock roundBegin = new RentrantLock();
    public static Condition roundStarts = roundBegin.newCondition();
    public static int roundNumber = 0;
    public Globals() {
    }
}