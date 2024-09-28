package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;
import java.util.Objects;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap wordMap;

    public HistoryTextHandler(NGramMap map){
        wordMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();

        StringBuilder response = new StringBuilder();
        for(String w:words){
            TimeSeries wordSeries = wordMap.weightHistory(w);
            if(!(Objects.equals(wordSeries, new TimeSeries())))
                response.append(w).append(": ").append(wordSeries.toString()).append("\n");
        }
        return response.toString();
    }
}
