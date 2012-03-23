package no.bekk.service;

import no.bekk.pojo.Operation;
import no.bekk.pojo.Parser;
import no.bekk.pojo.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 2.1
 */
public class Broker {
    private static Broker instance = new Broker();
    private static final Map<Long, Transaction> TRANSACTIONS = new HashMap<Long, Transaction>();
    private static final Map<Long, Parser> PARSERS = new HashMap<Long, Parser>();
    private static final Queue<Transaction> NEW = new ConcurrentLinkedQueue<Transaction>();
    private static AtomicLong TRANSACTION_ID = new AtomicLong(0);
    private static AtomicLong PARSER_ID = new AtomicLong(0);

    public static Broker getInstance() {
        return instance;
    }

    public Transaction addTransaction(Transaction.Type type, Operation data) {
        Transaction t = new Transaction(TRANSACTION_ID.incrementAndGet(), type, data);
        TRANSACTIONS.put(t.id, t);
        NEW.add(t);
        return t;
    }

    public Transaction findTransaction(Long id) {
        return TRANSACTIONS.get(id);
    }

    public Transaction findFirstReadyTransaction() {
        return NEW.poll();
    }

    public List<Transaction> findAll(Transaction.Status status) {
        List<Transaction> res = new ArrayList<Transaction>();
        for (Transaction transaction : TRANSACTIONS.values()) {
            if (transaction.is(status)) {
                res.add(transaction);
            }
        }
        Collections.reverse(res);
        return res;
    }


    private Random r = new Random(4435345);

    public Parser createParser() {
        try {
            int duration = r.nextInt(10) * 1000;
            Thread.sleep(duration);
            return persistParser(new Parser(duration));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Parser persistParser(Parser p) {
        p.id = PARSER_ID.incrementAndGet();
        PARSERS.put(p.id, p);
        return p;
    }

    public Parser findParser(Long id) {
        return PARSERS.get(id);
    }

    public List<Parser> findParsers() {
        return new ArrayList<Parser>(PARSERS.values());
    }
}
