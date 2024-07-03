/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import services.Interfaces.ISemanticaAnalysis;

/**
 *
 * @author ria.mishra
 */
public class SemanticAnalysisService implements ISemanticaAnalysis {
    private static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
        "good", "happy", "joy", "excellent", "great", "positive", "fortunate", "correct", "superior", "tasty", "yummy"
    ));

    private static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
            "bad", "sad", "angry", "poor", "terrible", "negative", "unfortunate", "wrong", "inferior", "tasteless","bad", "Not worth having","Bad experience"
    ));
    
    private static final Map<String, String> SENTIMENT_MAP = new HashMap<>();

    static {
        SENTIMENT_MAP.put("good", "Good");
        SENTIMENT_MAP.put("happy", "Happy");
        SENTIMENT_MAP.put("joy", "Joyful");
        SENTIMENT_MAP.put("excellent", "Excellent");
        SENTIMENT_MAP.put("great", "Great");
        SENTIMENT_MAP.put("positive", "Positive");
        SENTIMENT_MAP.put("fortunate", "Fortunate");
        SENTIMENT_MAP.put("correct", "Correct");
        SENTIMENT_MAP.put("superior", "Superior");
        SENTIMENT_MAP.put("tasty", "Tasty");
        SENTIMENT_MAP.put("yummy", "Yummy");
        SENTIMENT_MAP.put("bad", "Bad");
        SENTIMENT_MAP.put("sad", "Sad");
        SENTIMENT_MAP.put("angry", "Angry");
        SENTIMENT_MAP.put("poor", "Poor");
        SENTIMENT_MAP.put("terrible", "Terrible");
        SENTIMENT_MAP.put("negative", "Negative");
        SENTIMENT_MAP.put("unfortunate", "Unfortunate");
        SENTIMENT_MAP.put("wrong", "Wrong");
        SENTIMENT_MAP.put("inferior", "Inferior");
        SENTIMENT_MAP.put("tasteless", "Tasteless");
        SENTIMENT_MAP.put("not worth having", "Not Worth Having");
        SENTIMENT_MAP.put("bad experience", "Bad Experience");
    }
        
    public String analyzeSentiment(String text) {
        int positiveCount = 0;
        int negativeCount = 0;
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            if (POSITIVE_WORDS.contains(word)) {
                positiveCount++;
            } else if (NEGATIVE_WORDS.contains(word)) {
                negativeCount++;
            }
        }

        if (positiveCount > negativeCount) {
            return "Positive";
        } else if (negativeCount > positiveCount) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
    
    public String calculateAverageSentiment(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            if (SENTIMENT_MAP.containsKey(word)) {
                return SENTIMENT_MAP.get(word);
            }
        }

        if (text.toLowerCase().contains("not worth") && text.toLowerCase().contains("experience")) {
            return "Not Worth Experience";
        } else if (text.toLowerCase().contains("not good")) {
            return "Not Good";
        }

        return "Neutral";
    }
}
