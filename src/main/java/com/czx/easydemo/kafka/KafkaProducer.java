package com.czx.easydemo.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * @Description //TODO
 * @Date 2020/2/26 17:11
 * @Author GaoX
 */
@Component
@AllArgsConstructor
@Slf4j
public class KafkaProducer {

    //ProducerConfig类中存储了所有的Kafka配置参数

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron="0 0 17 * * ?")
    public void data(){
        LocalDate nowDate = LocalDate.now();
        String date = nowDate.toString();
        LocalTime nowTime = LocalTime.now();
        String time = nowTime.toString();

        try {
            kafkaTemplate.send("topic-test-01","当前时间为"+ date+" "+time );
        }catch (Exception e){
            e.printStackTrace();
            log.error("出错！！！！！！！！！！！");
        }

    }

}