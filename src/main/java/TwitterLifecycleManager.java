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

    private Thread consumerThread;
    private boolean continueConsumeRecords = true;

    @Override
    public void start() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TweetDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumer_demo");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("my-topic"));

        createThreadConsumer();
    }

    @Override
    public void stop() {
        this.continueConsumeRecords = false;
        try {
            this.consumerThread.join();
        } catch (InterruptedException e) {
            logger.error("Erro ao parar a thread de consumo de tweets.");
            e.printStackTrace();
        }
        this.consumer.unsubscribe();
        this.consumer.close();
    }

    private void createThreadConsumer() {
        this.consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(continueConsumeRecords) {
                    ConsumerRecords<String, Tweet> poll = consumer.poll(Duration.ofMillis(1000));
                    for (ConsumerRecord r: poll) {
                        logger.info(r.topic() + " - " + r.partition() + " - " + r.value());
                    }
                }
            }
        });
        this.consumerThread.start();
    }
}
