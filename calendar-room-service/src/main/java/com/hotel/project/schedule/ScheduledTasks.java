//package com.hotel.project.schedule;
//
//import com.hotel.project.dto.RoomCalendarResponse;
//import com.hotel.project.repository.RoomCalendarRepository;
//import com.hotel.project.service.MessageSources;
//import com.netflix.discovery.converters.Auto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class ScheduledTasks {
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    @Autowired
//    @Qualifier(MessageSources.ROOM_GET_OUTPUT)
//    private MessageChannel productDeleteChannel;
//
//    @Autowired
//    private RoomCalendarRepository roomCalendarRepository;
//
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }
//}
