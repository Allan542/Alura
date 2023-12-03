package Tarefa;

import java.util.concurrent.Callable;

public class Tarefa implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(10000);
        return "Oi";
    }
}
