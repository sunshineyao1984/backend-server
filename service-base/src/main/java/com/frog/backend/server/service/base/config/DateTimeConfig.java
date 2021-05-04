package com.frog.backend.server.service.base.config;

import com.frog.backend.components.commons.deserializer.LocalDateDeserializer;
import com.frog.backend.components.commons.deserializer.LocalDateTimeDeserializer;
import com.frog.backend.components.commons.deserializer.LocalTimeDeserializer;
import com.frog.backend.components.commons.util.TimeUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Description 日期时间配置
 *
 * @author yxy
 * @date 2020-10-12
 */
@Configuration
public class DateTimeConfig {

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     * 支持String类型的年月日“yyyy-MM-dd”和Long类型的毫秒数
     *
     * @return
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String s) {
                if (s.contains(TimeUtils.STRING_DATE_MARK)) {
                    return TimeUtils.stringToLocalDate(s, TimeUtils.TimeFormat.SHORT_DATE_PATTERN_LINE);
                }
                return TimeUtils.millisToLocalDate(Long.parseLong(s));
            }
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     * 支持String类型的年月日时分秒“yyyy-MM-dd HH:mm:ss”和Long类型的毫秒数
     *
     * @return
     */
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String s) {
                if (s.contains(TimeUtils.STRING_TIME_MARK)) {
                    return TimeUtils.stringToLocalDateTime(s);
                }
                return TimeUtils.millisToLocalDateTime(Long.parseLong(s));
            }
        };
    }

    /**
     * LocalTime转换器，用于转换RequestParam和PathVariable参数
     * 支持String类型的时分秒“HH:mm:ss”和Long类型的毫秒数
     *
     * @return
     */
    public Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String s) {
                if (s.contains(TimeUtils.STRING_TIME_MARK)) {
                    return TimeUtils.stringToLocalTime(s, TimeUtils.TimeFormat.TIME_PATTERN);
                }
                return TimeUtils.millisToLocalTime(Long.parseLong(s));
            }
        };
    }

    /**
     * Json序列化和反序列化转换器，用于转换Post请求体中的json以及将我们的对象序列化为返回响应的json
     * @return
     */

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //添加LocalDateTime序列化和反序列化器
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE.formatter));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer());
        //添加LocalDate序列化和反序列化器
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(TimeUtils.TimeFormat.SHORT_DATE_PATTERN_LINE.formatter));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer());
        //添加LocalTime序列化和反序列化器
        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(TimeUtils.TimeFormat.TIME_PATTERN.formatter));
        javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}
