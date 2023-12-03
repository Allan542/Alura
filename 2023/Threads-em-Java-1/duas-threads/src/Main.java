public class Main {
    public static void main(String[] args) {
        Runnable tarefa = new ImprimeNumeros();
        Thread threadNumeros = new Thread(tarefa);
        Thread threadNumeros2 = new Thread(tarefa);

        threadNumeros.start();
        threadNumeros2.start();
    }
}