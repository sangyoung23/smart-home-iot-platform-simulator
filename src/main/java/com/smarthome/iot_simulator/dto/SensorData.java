package com.smarthome.iot_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
    private String deviceId;            // 디바이스 ID
    private Double temperature;         // 온도
    private Double humidity;            // 습도

    private Double pm10;                // 미세먼지 PM10
    private Double pm25;                // 미세먼지 PM2.5
    private Integer co2;                // CO2 농도
    private Integer voc;                // VOC 수치
    private Integer light;              // 조도

    private Double powerUsage;          // 전력 사용량(W)
    private Double voltage;             // 전압(V)
    private Double current;             // 전류(A)
    private Double energyTotal;         // 누적 전력(kWh)

    private Boolean gasLeak;            // 가스 감지 여부
    private Integer smokeLevel;         // 연기 농도
    private Integer battery;            // 배터리 잔량(%)

    private LocalDateTime timestamp;             // 데이터가 측정된 시간
}
