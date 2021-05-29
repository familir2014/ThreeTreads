class ThreeTreads {
    static volatile char c = 'A';
    static Object titka = new Object();

    static class WaitNotifyClass implements Runnable {
        private char firstTR;
        private char twoTR;

        public WaitNotifyClass(int firstTR, int twoTR) {
            this.firstTR = (char) firstTR;
            this.twoTR = (char) twoTR;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (titka) {
                    try {
                        while (c != firstTR)
                            titka.wait();
                        System.out.print(firstTR);
                        c = twoTR;
                        titka.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("ThreeTreads");


        new Thread(new WaitNotifyClass('A', 'B')).start();
        new Thread(new WaitNotifyClass('B', 'C')).start();
        new Thread(new WaitNotifyClass('C', 'A')).start();
    }
}
