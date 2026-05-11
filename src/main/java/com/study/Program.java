package com.study;

import java.util.Set;

public class Program {

    public static void main(String[] args) {

        String text = """
        Java это мощный язык программирования.
        Программировать на Java — удовольствие!
        Это круто. Это просто.
        Я люблю программировать. Программирование — это искусство.
        """;

        Set<String> stopWords = Set.of("это", "на", "—", "и", "в", "я");
        StopWordsFilter filter = new StopWordsFilter(stopWords);

        TextAnalyzer analyzer = new TextAnalyzer();
        TextStats stats = analyzer.analyze(text, filter);

        System.out.println("=== Статистика текста ===");
        System.out.println(stats.totalChars());
        System.out.println(stats.totalSentences());
        System.out.println(stats.totalWords());

        System.out.println("\nТоп-10 самых частых слов:");
        stats.topWords().forEach((word, count) -> System.out.println(" " + word + ": " + count));


    }

}
