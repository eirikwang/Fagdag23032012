package no.bekk.quartz;

import no.bekk.pojo.Transaction;

import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
@Service
public class TransactionWorker implements Worker{
    private Random r = new Random(4435345);

    @Async
    public void doWork(Transaction transaction){
        transaction.startWork();
        try {
            System.out.println("[WORKER] Start work on " + transaction.id);
            Thread.sleep(r.nextInt(10) * 1000);
            transaction.done();
            System.out.println("[WORKER] Stop work on " + transaction.id);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
