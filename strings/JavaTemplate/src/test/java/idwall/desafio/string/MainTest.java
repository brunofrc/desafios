package idwall.desafio.string;

import idwall.desafio.Main;
import junit.framework.TestCase;

public class MainTest extends TestCase {

    private static final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
    private static final String DEFAULT_LIMIT = "40";
    private static final String JUSTIFY = "true";
    private static final String NOT_JUSTIFY = "false";

    public MainTest( String testName )
    {
        super( testName );
    }

    public void testApp()
    {
        String[] args0 = new String[]{DEFAULT_INPUT_TEXT, DEFAULT_LIMIT, NOT_JUSTIFY};
        Main.main(args0);

        String[] args1 = new String[]{DEFAULT_INPUT_TEXT, DEFAULT_LIMIT, JUSTIFY};
        Main.main(args1);

    }
}
