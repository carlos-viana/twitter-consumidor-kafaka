import twitter4j.*;

public class TweetListener implements StatusListener {

    private static final Logger logger = Logger.getLogger(TweetListener.class);

    @Override
    public void onStatus(Status status) {

    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        System.out.println("[DEBUG] At StatusListener - onDeletionNotice");
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("[DEBUG] At StatusListener - onTrackLimitationNotice");
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        System.out.println("[DEBUG] At StatusListener - onScrubGeo");
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        System.out.println("[DEBUG] At StatusListener - onStallWarning");
    }

    @Override
    public void onException(Exception ex) {
        logger.error("Erro no listener: \n" + ex.getMessage());
        ex.printStackTrace();
    }
}
