package hsAlgo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class Globals {
    public static Lock phaseBegins = new ReentrantLock();
    public static Condition phaseStarts = phaseBegins.newCondition();
    public static int phaseNumber = -1;
    public static Semaphore mutex = new Semaphore(1); 
    public static int endedProcesses = 0;
    public Globals() {
    }
}