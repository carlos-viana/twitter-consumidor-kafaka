import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class TwitterLifecycleManager implements LifecycleManager, Serializable {

    private static Logger logger = LoggerFactory.getLogger(TwitterLifecycleManager.class.getName());
    private KafkaConsumer<String, Tweet> consumer;

    @Override
    public void start() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TweetDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumer_demo");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("my_topic"));

        while(true) {
            ConsumerRecords<String, Tweet> poll = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord r: poll) {
                logger.info(r.topic() + " - " + r.partition() + " - " + r.value());
            }
        }

    }

    @Override
    public void stop() {
        this.consumer.unsubscribe();
        this.consumer.close();
    }

//   private void setTwitterStream() {
//        String _consumerKey = System.getenv().get("TWITTER_CONSUMER_KEY");
//        String _consumerSecret = System.getenv().get("TWITTER_CONSUMER_SECRET");
//        String _accessToken = System.getenv().get("TWITTER_ACCESS_TOKEN");
//        String _accessTokenSecret = System.getenv().get("TWITTER_ACCESS_TOKEN_SECRET");
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setOAuthConsumerKey(_consumerKey)
//                .setOAuthConsumerSecret(_consumerSecret)
//                .setOAuthAccessToken(_accessToken)
//                .setOAuthAccessTokenSecret(_accessTokenSecret);
//
//        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
//        this.twitterStream = tf.getInstance();
//    }

//    private void filterTweets() {
//        String trackedTerms = System.getenv().get("TWITTER_TRACKED_TERMS");
//        FilterQuery query = new FilterQuery();
//        query.track(trackedTerms.split(","));
//        this.twitterStream.filter(query);
//    }
}
