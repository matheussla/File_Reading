package br.com.abreu.matheus.file.reading;

import br.com.abreu.matheus.file.reading.service.FolderWatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class FileReadingApplication implements ApplicationListener<ApplicationReadyEvent> {

	private String path = System.getProperty("user.dir");

	public static void main(String args[]) {
		SpringApplication.run(FileReadingApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		try {
			new FolderWatcher(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
