package no.bekk.quartz;

import no.bekk.pojo.Transaction;
import no.bekk.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 2.1
 */
@Service
public class TransactionRunner implements Scheculer{
    private Broker broker = Broker.getInstance();

    @Autowired
    private Worker worker;

    public TransactionRunner() {
    }

    @Scheduled(fixedDelay = 1000)
    public void doWork() {
        Transaction transaction = broker.findFirstReadyTransaction();
        if (transaction != null) {
            worker.doWork(transaction);
        }
    }
}
