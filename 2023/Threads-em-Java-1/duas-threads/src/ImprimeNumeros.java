public class ImprimeNumeros implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            Thread threadAtual = Thread.currentThread();
            long id = threadAtual.getId();
            System.out.println(id + " - " + i);
        }
    }
}
