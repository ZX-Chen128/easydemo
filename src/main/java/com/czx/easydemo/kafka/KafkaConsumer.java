package com.czx.easydemo.kafka;

import com.czx.easydemo.mapper.LogMapper;
import com.czx.easydemo.model.Log;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * @Description //TODO
 * @Date 2020/2/26 17:13
 * @Author GaoX
 */
@Component
@Slf4j
public class KafkaConsumer {
    /*
    @Autowired
    LogMapper logMapper;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void data(ConsumerRecord consumerRecord) {
        Log data = new Log();
        Object value = consumerRecord.value();
        data.setTime((String) value);
        if (log.isInfoEnabled()) {
            log.info("offset {}, value {}", consumerRecord.offset(), consumerRecord.value());
            logMapper.insert(data);
        }
        if (null == value) {
            log.error("kafka消费数据为空");
        }
        log.info((String) value);


    }


     */
}