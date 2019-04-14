package com.pyankoff.word;

import org.springframework.core.style.ToStringCreator;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {

    @Id
    private String id;

    private Integer cnt;

    public Word() {
    }

    Word(String newWord) {
        id = newWord;
        cnt = 0;
    }

    public Word incCount() {
        cnt++;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("cnt", cnt)
                .toString();
    }
}
