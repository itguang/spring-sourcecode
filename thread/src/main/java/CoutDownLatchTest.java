import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CoutDownLatchTest {

    /**
     * 倒计时 3秒
     */
    private static final int RUNNER_NUMBER = 3;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        // 计时器
        CountDownLatch readyLatch = new CountDownLatch(RUNNER_NUMBER);

        for (int i = 0; i < 10; i++) {
            Sporter sporter = new Sporter(String.valueOf(i), readyLatch);
            new Thread(sporter).start();
        }

        // 开始倒计时3s
        for (int i = 0; i < RUNNER_NUMBER; i++) {
            Thread.sleep(1000);
            int countDown = RUNNER_NUMBER - i;
            System.out.println("倒计时：" + countDown);
            readyLatch.countDown();
        }
    }

    static class Sporter implements Runnable {

        private CountDownLatch readyLatch;
        private String name;

        public Sporter(String name, CountDownLatch readyLatch) {
            this.name = name;
            this.readyLatch = readyLatch;
        }

        @Override
        public void run() {
            // 如果计时器不为0，则一直等待，直到计时器为0
            waitRun(readyLatch);
            // 模拟运动员出发延迟
            RANDOM.nextInt(1000);
            System.out.println("运动员：" + name + "号出发");
        }

        /**
         * 等待跑步倒计时
         */
        private void waitRun(CountDownLatch readyLatch) {
            try {
                readyLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
