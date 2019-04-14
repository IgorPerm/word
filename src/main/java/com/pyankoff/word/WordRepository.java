package com.pyankoff.word;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, String> {
    List<Word> findTop10ByOrderByCntDesc();
}
