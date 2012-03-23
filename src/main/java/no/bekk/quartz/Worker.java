package no.bekk.quartz;

import no.bekk.pojo.Transaction;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 2.1
 */
public interface Worker {
    public void doWork(Transaction transaction);
}
