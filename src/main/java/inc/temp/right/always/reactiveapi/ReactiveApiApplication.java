package inc.temp.right.always.reactiveapi;

import inc.temp.right.always.reactiveapi.services.TemperatureMeasurementsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ReactiveApiApplication implements CommandLineRunner {
	@Autowired
	private TemperatureMeasurementsService temperatureMeasurementsService;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		temperatureMeasurementsService.anomalies().subscribe(s -> log.info(String.format("Message from Kafka: %s.", s)));
	}
}
