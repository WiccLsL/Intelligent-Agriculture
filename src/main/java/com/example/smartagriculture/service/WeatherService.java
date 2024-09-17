package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.WeatherData;
import com.example.smartagriculture.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.smartagriculture.util.MD5Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${baidu.translate.api.key}")
    private String translateApiKey; // 百度翻译 API 密钥

    @Value("${baidu.translate.api.appid}")
    private String appId;  // 百度翻译应用 ID

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}&units=metric";
    private final String TRANSLATE_API_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate?q={query}&from=zh&to=en&appid={appid}&salt={salt}&sign={sign}";

    public WeatherData getWeatherData(String city) {
        try {
            // 第一步: 将中文城市名翻译为英文
            String translatedCityName = translateCityName(city);

            // 第二步: 使用 OpenWeather API 获取天气数据
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap<>();
            params.put("city", translatedCityName);  // 使用翻译后的城市名
            params.put("key", apiKey);

            ResponseEntity<Map> response = restTemplate.getForEntity(WEATHER_API_URL, Map.class, params);
            Map<String, Object> weatherMap = response.getBody();

            if (weatherMap != null) {
                WeatherData weatherData = new WeatherData();
                weatherData.setCity(city);  // 保存原始的城市名称

                // 处理 "weather" 部分
                List<Map<String, Object>> weatherList = (List<Map<String, Object>>) weatherMap.get("weather");
                if (weatherList != null && !weatherList.isEmpty()) {
                    weatherData.setDescription(weatherList.get(0).get("description").toString());
                }

                // 处理 "main" 部分
                Map<String, Object> mainMap = (Map<String, Object>) weatherMap.get("main");
                if (mainMap != null) {
                    weatherData.setTemperature(Double.parseDouble(mainMap.get("temp").toString()));
                    weatherData.setHumidity(Double.parseDouble(mainMap.get("humidity").toString()));
                }

                // 处理 "wind" 部分
                Map<String, Object> windMap = (Map<String, Object>) weatherMap.get("wind");
                if (windMap != null) {
                    weatherData.setWindSpeed(Double.parseDouble(windMap.get("speed").toString()));
                }

                weatherData.setTimestamp(LocalDateTime.now());

                // 保存到数据库并返回
                return weatherDataRepository.save(weatherData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用百度翻译 API 将中文城市名翻译为英文
    private String translateCityName(String cityName) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("query", cityName);
        params.put("appid", appId);
        String salt = String.valueOf(System.currentTimeMillis());  // 生成随机盐值
        params.put("salt", salt);

        // 生成签名：appid + query + salt + 密钥 的 MD5 加密
        String sign = appId + cityName + salt + translateApiKey;
        String md5Sign = MD5Utils.md5(sign);  // 需要实现一个 MD5 工具类
        params.put("sign", md5Sign);

        ResponseEntity<Map> response = restTemplate.getForEntity(TRANSLATE_API_URL, Map.class, params);
        Map<String, Object> translateResult = response.getBody();

        if (translateResult != null && translateResult.get("trans_result") != null) {
            List<Map<String, String>> transResultList = (List<Map<String, String>>) translateResult.get("trans_result");
            if (!transResultList.isEmpty()) {
                return transResultList.get(0).get("dst");  // 获取翻译后的城市名称
            }
        }
        throw new Exception("City name translation failed");
    }
}
