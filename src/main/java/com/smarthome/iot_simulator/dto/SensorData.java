package com.smarthome.iot_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
    private String deviceId; // 디바이스 고유 ID
    private double temperature; // 센서가 측정한 온도 값
    private double humidity; // 센서가 측정한 습도 값
    private long timestamp; // 데이터가 측정된 시간
}
