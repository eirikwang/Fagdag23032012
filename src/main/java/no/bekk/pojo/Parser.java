package no.bekk.pojo;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
public class Parser {
    public Long id;
    public String value;
    public Integer duration;

    public Parser(Long id) {
        this.id = id;
        this.value = "verdi " + id;
    }

    public Parser(Integer duration) {
        this.duration = duration;
    }
}
