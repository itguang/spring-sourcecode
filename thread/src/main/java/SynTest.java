public class SynTest {
    public int i;
    Object obj = new Object();

    public void syncTask() {
        synchronized (obj) {
            i++;
        }
    }

    public void syncTask2() {
        synchronized (this) {
            i++;
        }
    }
}