package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private class WordSeries extends TreeMap<String,TimeSeries> {
        public WordSeries(){super();}
    }

    private WordSeries ownWordSeries;
    private TimeSeries YearDatas;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In wordFilein = new In(wordsFilename);
        In countsFilein = new In(countsFilename);
        ownWordSeries = new WordSeries();
        YearDatas = new TimeSeries();

        while(!wordFilein.isEmpty()){
            String nextLine = wordFilein.readLine();
            String[] splitLine = nextLine.split("\t");
            if(!ownWordSeries.containsKey(splitLine[0])){
                TimeSeries temp = new TimeSeries();
                temp.put(Integer.parseInt(splitLine[1]),Double.parseDouble(splitLine[2]));
                ownWordSeries.put(splitLine[0],temp);
            }
            else{
                ownWordSeries.get(splitLine[0]).put(Integer.parseInt(splitLine[1]),Double.parseDouble(splitLine[2]));
            }
        }

        while (!countsFilein.isEmpty()){
            String nextLine = countsFilein.readLine();
            String[] splitLine = nextLine.split(",");
            YearDatas.put(Integer.parseInt(splitLine[0]),Double.parseDouble(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        for(int y = startYear;y <= endYear;y++){
            double data = ownWordSeries.get(word).get(y);
            result.put(y,data);
        }
        return result;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        TimeSeries Origin = ownWordSeries.get(word);
        if(Origin == null)
            return result;
        List<Integer> yearsList = Origin.years();
        for(int y:yearsList){
            result.put(y,Origin.get(y));
        }
        return result;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries result = YearDatas;
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        TimeSeries wordTimeSeries = ownWordSeries.get(word);
        for(int i = startYear;i <= endYear;i++){
            result.put(i,wordTimeSeries.get(i)/ YearDatas.get(i));
        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        TimeSeries wordTimeSeries = ownWordSeries.get(word);
        if(wordTimeSeries == null)
            return result;
        List<Integer> yearsList = wordTimeSeries.years();
        for(int y:yearsList){
            result.put(y,wordTimeSeries.get(y)/ YearDatas.get(y));
        }
        return result;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        for(int y = startYear;y <= endYear;y++){
            double summed_rf = 0.0;
            for(String w:words){
                summed_rf += weightHistory(w,startYear,endYear).get(y);
            }
            result.put(y,summed_rf);
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        for(int y = MIN_YEAR;y <= MAX_YEAR;y++){
            double summed_rf = 0.0;
            for(String w:words){
                summed_rf += weightHistory(w,MIN_YEAR,MAX_YEAR).get(y);
            }
            result.put(y,summed_rf);
        }
        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
