package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryHandler extends NgordnetQueryHandler  {
    private NGramMap wordMap;

    public HistoryHandler(NGramMap map){
        wordMap = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for(String w:words){
            TimeSeries wordSeries = wordMap.weightHistory(w);
            if(!(Objects.equals(wordSeries, new TimeSeries()))){
                labels.add(w);
                lts.add(wordSeries);
            }
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
