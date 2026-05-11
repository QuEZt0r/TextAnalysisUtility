package com.study;

import java.util.Map;

public record TextStats(int totalChars, int totalSentences, int totalWords, Map<String, Integer> topWords) {}