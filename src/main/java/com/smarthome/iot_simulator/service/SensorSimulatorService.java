package com.smarthome.iot_simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smarthome.iot_simulator.dto.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SensorSimulatorService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private double energyTotal = 0.0; // 누적 전력(kWh)

    @Scheduled(fixedRate = 5000)
    public void sendSensorData() throws JsonProcessingException {
        SensorData data = new SensorData();

        data.setDeviceId("device-01");

        // 환경 센서
        data.setTemperature(randomDouble(0, 40));
        data.setHumidity(randomDouble(0, 100));
        data.setPm10(randomDouble(0, 150));
        data.setPm25(randomDouble(0, 80));
        data.setCo2(randomInt(400, 2000));
        data.setVoc(randomInt(0, 500));
        data.setLight(randomInt(0, 2000));

        // 전력 센서
        double powerUsage = randomDouble(0, 300); // W
        data.setPowerUsage(powerUsage);
        data.setVoltage(randomDouble(200, 240));
        data.setCurrent(randomDouble(0, 10));

        // 누적 전력 증가
        energyTotal += powerUsage / 3600 / 1000; // W -> kWh 변환
        data.setEnergyTotal(energyTotal);

        // 위험 감지 센서
        data.setGasLeak(Math.random() < 0.05); // 5% 확률로 감지(true)
        data.setSmokeLevel(randomInt(0, 100));
        data.setBattery(randomInt(50, 100));

        // 시간
        data.setTimestamp(LocalDateTime.now());

        // JSON 변환
        String json = objectMapper.writeValueAsString(data);

        kafkaTemplate.send("sensor-topic", json);
    }

    private double randomDouble(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    private int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }
}
