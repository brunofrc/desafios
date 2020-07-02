package idwall.domain.services.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import idwall.domain.dto.SubredditThread;
import idwall.domain.exception.InvalidCommandException;
import idwall.domain.services.crawler.interfaces.ICrawler;
import idwall.domain.services.entryProcessor.interfaces.IEntryProcessor;
import idwall.domain.services.telegramBot.interfaces.IBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Fernandes.
 */
@Component
public class Bot implements IBot {

    @Autowired
    ICrawler crawler;

    @Autowired
    IEntryProcessor processor;

    /**
     * Method responsible for running the telegram bot
     */
    public void run() {
        TelegramBot telegramBot = new TelegramBot("1129437209:AAEk9H-xOQAeOXT0R-LRurMWMMxPt54iwYE");

        System.out.println("bot running: t.me/idwall_bruno_bot");

        Integer offSet = 0;

        while (true) {

            GetUpdates getUpdates = new GetUpdates().limit(5).offset(offSet).timeout(0);

            GetUpdatesResponse updatesResponse = telegramBot.execute(getUpdates);

            List<Update> updates = updatesResponse.updates();

            for (Update update : updates) {

                offSet = update.updateId() + 1;
                Long chatId = update.message().chat().id();
                String request = update.message().text();

                try {
                    buildAndSendAnswer(request, chatId, telegramBot);

                } catch (InvalidCommandException e) {
                    sendMessage(telegramBot, chatId,
                            "Invalid command. Try: /NadaPraFazer nameOfYourThread;nameOfYourSecondThread");
                }
            }
        }
    }

    /**
     * Method responsible for invoking buildResult and sendResult methods
     * @param request
     * @param chatId
     * @param telegramBot
     */
    private void buildAndSendAnswer(String request, Long chatId, TelegramBot telegramBot) {

        List<SubredditThread> result = buildResult(request, chatId, telegramBot);

        sendResult(result, chatId, telegramBot);
    }

    /**
     * Method responsible for process the request and build a result
     * @param request
     * @param chatId
     * @param telegramBot
     * @return
     */
    private List<SubredditThread> buildResult(String request, Long chatId, TelegramBot telegramBot) {

        String[] subreddits = processor.process(request);

        List<SubredditThread> result = new ArrayList<>();

        for (String subreddit : subreddits) {
            List<SubredditThread> subResult = crawler.processSubreddit(subreddit);

            if(subResult.isEmpty()) {
                sendMessage(telegramBot, chatId,
                        "No top thread found for subreddit: " + subreddit);
            }else{
                result.addAll(subResult);
            }
        }
        return result;
    }

    /**
     * Method responsible for invoking sendMessage method for each result
     * @param result
     * @param chatId
     * @param bot
     */
    private void sendResult(List<SubredditThread> result, Long chatId, TelegramBot bot) {
        for (SubredditThread thread : result) {
            sendMessage(bot, chatId, thread.toString());
        }
    }

    /**
     * Method responsible for sending a message to the client
     * @param telegramBot
     * @param chatId
     * @param text
     */
    private void sendMessage(TelegramBot telegramBot, Long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
}
