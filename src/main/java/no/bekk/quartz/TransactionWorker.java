package no.bekk.quartz;

import no.bekk.pojo.Parser;
import no.bekk.pojo.Transaction;
import no.bekk.service.Broker;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
@Service
public class TransactionWorker implements Worker{
    Broker broker = Broker.getInstance();

    @Async
    public void doWork(Transaction transaction){
        transaction.startWork();
        try {
            System.out.println("[WORKER] Start work on " + transaction.id);
            Parser p = broker.createParser();
            transaction.resultId = p.id;
            transaction.done();
            System.out.println("[WORKER] Stop work on " + transaction.id);
        } catch (RuntimeException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
