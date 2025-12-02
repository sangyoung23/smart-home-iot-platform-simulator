package com.smarthome.iot_simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smarthome.iot_simulator.dto.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class SensorSimulatorService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    public void sendSensorData() throws JsonProcessingException {
        SensorData data = new SensorData();
        data.setDeviceId("device-01");
        data.setTemperature(Math.random() * 40);
        data.setHumidity(Math.random() * 100);
        data.setTimestamp(System.currentTimeMillis());

        String json = objectMapper.writeValueAsString(data);
        kafkaTemplate.send("sensor-topic", json);

    }
}
