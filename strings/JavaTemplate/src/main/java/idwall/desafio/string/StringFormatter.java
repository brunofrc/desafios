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

        String[] lineInputs = text.replace("\n", " ").split(" ");
        for (String word : lineInputs) {
            {
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


    protected String removeBlankEnd(String line) {
        if (line.endsWith(" ")) {
            return line.substring(0, line.length() - 1);
        }
        return line;
    }

    protected abstract String formatResult(List<String> lines);
}
