package com.pyankoff.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class WordApplication implements CommandLineRunner {

    @Autowired
    private WordRepository wordRepository;

    public static void main(String[] args) {
        SpringApplication.run(WordApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/acer/Desktop/input1.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] normalized = normalize(line).split(" ");
            for (String nd: normalized) {
                if (!nd.isEmpty()) {
                    Word word = getWord(nd);
                    word = word.incCount();
                    wordRepository.save(word);
                }
            }
        }

        List<Word> words = wordRepository.findTop10ByOrderByCntDesc();
        System.out.println(words);
    }

    private Word getWord(String target) {
        Optional<Word> optWord = wordRepository.findById(target);
        return optWord.orElseGet(() -> new Word(target));
    }

    private String normalize(String token) {
        return token.replaceAll("(?U)[\\p{Punct}|\\s|\\d)]+", " ").trim().toLowerCase();
    }
}
