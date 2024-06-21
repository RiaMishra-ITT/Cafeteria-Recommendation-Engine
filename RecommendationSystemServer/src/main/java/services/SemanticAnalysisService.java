/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;


import java.util.Arrays;
import java.util.HashSet;
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
            "bad", "sad", "angry", "poor", "terrible", "negative", "unfortunate", "wrong", "inferior"
    ));
        
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
}
