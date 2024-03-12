package inc.temp.right.always.reactiveapi.services;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class TemperatureMeasurementsService {
    @Value("${main.config.kafkaTopicName}")
    private String kafkaTopicName;
    @Value("${main.config.kafkaBootstrapServers}")
    private String kafkaBootstrapServers;
    @Value("${main.config.kafkaGroupId}")
    private String kafkaGroupId;
    @Value("${main.config.kafkaOffsets}")
    private String kafkaOffsets;

    public Flux<String> anomalies() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaOffsets);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        ReceiverOptions<Integer, String> receiverOptions = ReceiverOptions.<Integer, String>create(consumerProps).subscription(Collections.singleton(kafkaTopicName));

        KafkaReceiver<Integer, String> receiver = KafkaReceiver.create(receiverOptions);
        return receiver.receive().map(ConsumerRecord::value);
    }
}
