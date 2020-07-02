package idwall.domain.services.crawler;


import idwall.domain.dto.SubredditThread;
import idwall.domain.exception.InvalidCommandException;
import idwall.domain.services.crawler.interfaces.ICrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Fernandes.
 */
@Component
public class Crawler implements ICrawler {

    public List<SubredditThread> processSubreddit(String thread){
        List<SubredditThread> result = new ArrayList<>();

        String topUrl = "https://www.reddit.com/r/" + thread.trim() + "/top/";

        Document document = connectWithReddit(topUrl);

        Elements items = findThreads(document);

        for (Element item : items) {
            if(isTop(item)) {

                SubredditThread dto = createNewDtoBasedOnItem(item, thread);

                result.add(dto);
            }

        }

        return result;
    }

    private Document connectWithReddit(String url){
        try {
            return Jsoup.connect(url).get();
        }catch (Exception ex){
            throw new InvalidCommandException();
        }
    }

    private Elements findThreads(Document document){
        return document.select(".scrollerItem");
    }

    private boolean isTop(Element item){

        Integer score = findElementScore(item);

        if(score < 5000){
            return false;
        }

        return true;
    }

    private SubredditThread createNewDtoBasedOnItem(Element item, String thread){
        SubredditThread dto = new SubredditThread();

        dto.setTitle(findElementTitle(item));
        dto.setUpvotes(findElementScore(item));
        dto.setRedditLink("https://www.reddit.com/r/" + thread.trim());
        dto.setCommentsLink("https://www.reddit.com" + findCommentsLink(item));

        return dto;
    }

    private Integer findElementScore(Element item){
        String scoreText = item.select(" ._25IkBM0rRUqWX5ZojEMAFQ").text();
        try {
            if (scoreText.contains("k")) {

                String formattedScore;

                String[] decimal = scoreText.split("\\.");

                if(decimal.length > 1) {
                    decimal[1] = decimal[1].replace("k", "");
                    while (decimal[1].length() != 3) {
                        decimal[1] = decimal[1] + "0";
                    }
                    formattedScore = decimal[0] + decimal[1];
                }else{
                    decimal[0] = decimal[0] + "000";
                    formattedScore = decimal[0];
                }

                return Integer.valueOf(formattedScore);
            } else {
                return Integer.valueOf(scoreText);
            }
        }catch (NumberFormatException ex){
            return 0;
        }
    }

    private String findElementTitle(Element item){
        return item.select("._eYtD2XCVieq6emjKBH3m").text();
    }

    private String findCommentsLink(Element item){
        return item.select("._1UoeAeSRhOKSNdY_h3iS1O").attr("href");

    }
}
