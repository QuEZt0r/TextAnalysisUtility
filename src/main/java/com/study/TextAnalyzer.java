package com.study;

import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

    private int countSentences(String text) {
        if (text == null || text.isBlank()) return 0;

        String[] sentences = text.split("[.!?]+");
        int count = 0;
        for (String s : sentences) {
            if (!s.trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private List<String> tokenize(String text) {
        String[] arrayWords = text.toLowerCase().replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "").split("\\s+");
        return Arrays.asList(arrayWords);
    }

    private Map<String, Integer> countWordFrequencies(List<String> words) {
        Map<String, Integer> frequencies = new HashMap<>();
        for(String word : words) {
            if(word.isEmpty()){
                continue;
            }
            if(frequencies.containsKey(word)) {
                frequencies.put(word, frequencies.get(word) + 1);
            }
            else {
                frequencies.put(word, 1);
            }
        }
        return new HashMap<>(frequencies);
    }

    private Map<String, Integer> getTopNWords(Map<String, Integer> frequencyMap, int n) {
        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public TextStats analyze(String text, StopWordsFilter filter) {
        List<String> words = tokenize(text);
        List<String> filteredWords = (filter != null) ? filter.filter(words) : words;
        Map<String, Integer> frequencies = countWordFrequencies(filteredWords);
        Map<String, Integer> topWords = getTopNWords(frequencies, 10);

        int totalChars = text.length();
        int totalSentences = countSentences(text);
        int totalWords = filteredWords.size();

        return new TextStats(totalChars, totalSentences, totalWords, topWords);
    }

}
