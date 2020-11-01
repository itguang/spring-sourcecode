import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static final int THREAD_NUMBER = 10;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUMBER, new Runnable() {
            @Override
            public void run() {
                System.out.println("运动员们都准备好了！等待裁判倒计时...");
            }
        });

        // 10 个运动员开始准备
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Sporter sporter = new Sporter(String.valueOf(i), barrier);
            new Thread(sporter).start();
        }
    }

    static class Sporter implements Runnable {
        private String name;
        private CyclicBarrier barrier;

        public Sporter(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("运动员：" + name + " 号准备完毕！");
            // 告知裁判，等待出发
            awaitRun();
        }

        private void awaitRun() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
