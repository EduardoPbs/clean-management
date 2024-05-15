package br.com.lgmanagement.lgManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@SpringBootApplication
public class LgManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgManagementApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void Listener(){
		System.out.println("*".repeat(48));
		System.out.println("* App Started at " + LocalDateTime.now() + " *");
		System.out.println("*".repeat(48));
	}

}
