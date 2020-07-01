package idwall.desafio.string;

public class FormatterFactory {

    public static StringFormatter getFormatter(boolean justify){

        if(justify){
            return new IdwallJustifier();
        }

        return new IdwallFormatter();
    }
}
