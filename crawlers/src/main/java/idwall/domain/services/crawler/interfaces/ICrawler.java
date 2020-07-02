package idwall.domain.services.crawler.interfaces;

import idwall.domain.dto.SubredditThread;

import java.util.List;

/**
 * Created by Bruno Fernandes.
 */
public interface ICrawler {

    List<SubredditThread> processSubreddit(String thread);

}
