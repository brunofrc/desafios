package idwall.desafio.string;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param lines
     * @return
     */
    @Override
    protected String formatResult(List<String> lines){
        StringBuilder result = new StringBuilder();

        for(String line : lines){

            line = removeBlankEnd(line);

            result.append(line + "\n");

            if(line.endsWith(" ")){
                result.append("\n");
            }
        }

        return result.toString();
    }
}