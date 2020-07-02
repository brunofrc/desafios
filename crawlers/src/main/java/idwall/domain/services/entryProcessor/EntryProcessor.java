package idwall.domain.services.entryProcessor;

import idwall.domain.exception.InvalidCommandException;
import idwall.domain.services.entryProcessor.interfaces.IEntryProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Bruno Fernandes.
 */
@Component
public class EntryProcessor implements IEntryProcessor {

    /**
     * Method responsible for process the input
     * and return an array of string containing each desire subreddit
     * @param input
     * @return
     */
    public String[] process(String input){
        if(input == null || input.isEmpty()){
            throw new InvalidCommandException();
        }
        String[] formattedInput = input.replace("/NadaPraFazer","").split(";");
        if(formattedInput.length == 0){
            throw new InvalidCommandException();
        }
        return formattedInput;
    }
}
