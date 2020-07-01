package idwall.desafio.string;

import java.util.List;

/**
 * Created by Bruno Fernandes.
 */
public class IdwallJustifier extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param lines
     * @return
     */
    @Override
    protected String formatResult(List<String> lines){
        StringBuilder result = new StringBuilder();
        String breaker;
        for(String line : lines){

            breaker = findBreakerWordInLine(line);

            while(line.endsWith(" ")){
                line = removeBlankEnd(line);
            }

            String[] words = initializeWords(line);

            justifyLine(line, words);

            String newLine = buildResultLine(words, breaker);

            result.append(newLine + "\n");
        }

        return result.toString();
    }

    private String findBreakerWordInLine(String line){
        //if the line ends with two spaces
        //it means the original text had \n\n
        //so at this point we have the text breaker
        if(line.endsWith("  ")){
            line = line.substring(0 , line.length() - 2);
            return  line.substring(line.lastIndexOf(" ")+1);
        }
        return "";
    }

    private String[] initializeWords(String line){
        String[] words = line.split(" ");
        //insert the first space between words
        //after split the string by spaces
        for(int i = 0; i < words.length - 1 ;i++) {
            words[i] = words[i] + " ";
        }
        return words;
    }

    /**
     * Method responsible for justify each line returning the line's words with spaces
     * @param line
     * @param words
     */
    private void justifyLine(String line, String[] words){
        int lineLength = line.length();

        boolean lineJustified = false;

        while(!lineJustified){
            //go through each word excluding the last,
            //append a new space until the new line
            //length is equals to the limit
            for(int i = 0; i < words.length - 1 ;i++) {
                if (lineLength == limit) {
                    lineJustified = true;
                    break;
                } else{
                    words[i] = words[i] + " ";
                    lineLength++;
                }
            }
        }
    }

    /**
     * Method responsible for build a new line based on
     * the justified words and the text breaker word
     * @param words
     * @param breaker
     * @return
     */
    private String buildResultLine(String[] words, String breaker){
        StringBuilder newline = new StringBuilder();
        for(String s : words) {
            newline.append(s);
            //if we found the text breaker insert an extra \n
            //in order to break the text
            if(s.equals(breaker)){
                newline.append("\n");
            }
        }
        return newline.toString();
    }
}
