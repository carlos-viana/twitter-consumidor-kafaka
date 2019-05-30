import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class TweetDeserializer implements Deserializer<Tweet> {

    private ObjectMapper mapper = new ObjectMapper();

    public TweetDeserializer() {
        JavaTimeModule jtm = new JavaTimeModule();
        mapper.registerModule(jtm);
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Tweet deserialize(String s, byte[] bytes) {
        Tweet tweet = null;
        try {
            tweet = mapper.readValue(bytes, Tweet.class);
        } catch (IOException e) {
            System.out.println("[DEBUG] TweetDeserializer - IOException at deserialize");
            e.printStackTrace();
        }
        return tweet;
    }

    @Override
    public void close() {
    }
}
