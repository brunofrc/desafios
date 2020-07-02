package idwall.application;

import idwall.domain.dto.SubredditThread;
import idwall.domain.exception.InvalidCommandException;
import idwall.domain.services.crawler.interfaces.ICrawler;
import idwall.domain.services.entryProcessor.interfaces.IEntryProcessor;
import idwall.domain.services.telegramBot.interfaces.IBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Fernandes.
 */
@Component
@ComponentScan("idwall.domain")
public class ApplicationController implements IApplicationController {

    @Autowired
    private ICrawler crawler;

    @Autowired
    private IEntryProcessor entryProcessor;

    @Autowired
    private IBot telegramBot;

    /**
     * Method responsible for calling crawler service and print the results
     */
    public void printSubRedditsDetail(String input) throws InvalidCommandException {
        String[] subreddits = entryProcessor.process(input);
        List<SubredditThread> result = new ArrayList<>();
        for(String subreddit: subreddits) {
            result.addAll(crawler.processSubreddit(subreddit));
        }
        printResult(result);
    }

    /**
     * Method responsible for calling telegramBot service and run it
     */
    public void startTelegramBot(){
        telegramBot.run();
    }

    /**
     * Method responsible for printing all subreddit threads
     * @param subredditThreadList
     */
    private void printResult(List<SubredditThread> subredditThreadList){
        for(SubredditThread subredditThread : subredditThreadList){
            System.out.println("Subreddit: " + subredditThread.getRedditLink());
            System.out.println("Thread: " + subredditThread.getTitle());
            System.out.println("Up votes: " + subredditThread.getUpvotes());
            System.out.println("Comments: " + subredditThread.getCommentsLink());
            System.out.println("-------\n\n");
        }
    }

}
