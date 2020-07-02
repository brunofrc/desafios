package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    protected Integer limit;

    public StringFormatter() {
        this.limit = 40;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public String format(String text, Integer limit) {
        this.limit = limit;
        List<String> lines = new ArrayList<>();

        int lineLength = 0;
        StringBuilder currentLine = new StringBuilder();

        String[] wordsInputs = splitIntoWordsArray(text);

        for (String word : wordsInputs) {
            {
                //if inserting the new word into the
                //current line exceeds the limit
                //insert it into a new line
                if (lineLength + word.length() > limit) {
                    lines.add(currentLine.toString());
                    currentLine = new StringBuilder();
                    currentLine.append(word)
                            .append(" ");
                    lineLength = word.length() + 1;
                } else {
                    currentLine.append(word)
                            .append(" ");
                    lineLength += word.length() + 1;
                }
            }
        }
        lines.add(currentLine.toString());

        return formatResult(lines);
    }

    /**
     * Method responsible for breaking the text into an array of words
     * @param text
     * @return
     */
    private String[] splitIntoWordsArray(String text){
        return removeLineBreaker(text).split(" ");
    }

    /**
     * Method responsible for replacing the line breaker into space
     * @param text
     * @return
     */
    private String removeLineBreaker(String text){
        return text.replace("\n", " ");
    }


    /**
     * Method responsible for removing the last blank space
     * @param line
     * @return
     */
    protected String removeBlankEnd(String line) {
        if (line.endsWith(" ")) {
            return line.substring(0, line.length() - 1);
        }
        return line;
    }

    /**
     * protected method that will be implemented by class's child
     * according to a specific logic
     * @param lines
     * @return
     */
    protected abstract String formatResult(List<String> lines);
}
