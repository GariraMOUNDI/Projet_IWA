package org.projet_iwa.location.api.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.projet_iwa.location.api.model.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;
import java.util.*;

@EnableKafka
@Service
public class KafkaConsumerConfig {

    public static double current_lat, current_long;

//    @Bean
//    public ConsumerFactory<String, LocationDTO> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapAddress);
//        props.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                groupId);
//        props.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, LocationDTO> filterKafkaListenerContainerFactoryByDate() {
//        ConcurrentKafkaListenerContainerFactory<String, LocationDTO> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordFilterStrategy(
//                record -> distance(record.value().getLatitude(), record.value().getLongitude(), 0, 0)
//        );
//        return factory;
//    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @return Distance in Meters
     * @author David George
     */
    private static boolean distance(double lat2, double long2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - current_lat);
        double lonDistance = Math.toRadians(long2 - current_long);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(current_lat)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance) < 5;
    }

    public static void setCurrentLocation(Long lat, Long lon){
        current_long = lon;
        current_lat = lat;
    }

    public static List<LocationDTO> poll(String server, String group_id, String topic){
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                group_id);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        KafkaConsumer<String, LocationDTO> kafkaLocationConsumer = new KafkaConsumer<>(props);
        kafkaLocationConsumer.subscribe(Collections.singletonList(topic));

        List<LocationDTO> alert_list = new ArrayList<>();

        ConsumerRecords<String, LocationDTO> records = kafkaLocationConsumer.poll(1);

        for (ConsumerRecord<String, LocationDTO> record : records){
            System.out.println(record.topic()+"  "+ record.partition()+"  "+ record.offset()+"  "+
                    record.key()+"  "+ record.value());
            if (distance(record.value().getLatitude(), record.value().getLongitude(), 0, 0))
                alert_list.add(record.value());
        }

        kafkaLocationConsumer.commitAsync();
        kafkaLocationConsumer.close();

        return alert_list;
    }
}
