package com.hotel.project;

import com.hotel.project.service.MessageSources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBinding(MessageSources.class)
@EnableScheduling
public class CalendarRoomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarRoomServiceApplication.class, args);
	}

}
