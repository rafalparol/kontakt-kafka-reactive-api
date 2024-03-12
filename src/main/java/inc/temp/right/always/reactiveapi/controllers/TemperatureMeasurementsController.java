package inc.temp.right.always.reactiveapi.controllers;

import inc.temp.right.always.reactiveapi.services.TemperatureMeasurementsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@Log4j2
public class TemperatureMeasurementsController {
    @Autowired
    private TemperatureMeasurementsService temperatureMeasurementsService;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> anomalies() {
        return temperatureMeasurementsService.anomalies();
    }

    @GetMapping(value = "/trivialStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> trivialStream() {
        return Flux.interval(Duration.ofSeconds(1L));
    }

    public void setTemperatureMeasurementsService(TemperatureMeasurementsService temperatureMeasurementsService) {
        this.temperatureMeasurementsService = temperatureMeasurementsService;
    }
}
