package no.bekk.pojo;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
public class Transaction extends DomainClass {
    public Long id;
    public Type operation;
    public Operation data;
    public Status status;
    public Result result;
    public Long resultId;
    public ResultError resultError;

    public Transaction(Long id, Type type, Operation data) {
        this.id = id;
        this.operation = type;
        this.data = data;
        this.status = Status.Ready;
    }

    public boolean is(Status status) {
        return status == null || this.status.equals(status);
    }


    public enum Status {
        Ready,
        InProgress,
        Done
    }

    public enum Result {
        Success,
        Error
    }

    public enum Type {
        Parse
    }

    public void startWork() {
        this.status = Status.InProgress;
    }

    public void done() {
        this.status = Status.Done;
    }

}
