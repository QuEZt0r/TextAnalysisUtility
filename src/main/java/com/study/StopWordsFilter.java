package com.study;

import java.util.*;
import java.util.stream.Collectors;

public class StopWordsFilter {

    private final Set<String> stopWords;

    public StopWordsFilter(Set<String> stopWords) {
        this.stopWords = new HashSet<>(stopWords);
    }

    public List<String> filter(List<String> words) {
        return words.stream().filter(w -> !stopWords.contains(w)).collect(Collectors.toList());
    }

    public void addStopWord(String word) {
        stopWords.add(word.toLowerCase().trim());
    }

}
