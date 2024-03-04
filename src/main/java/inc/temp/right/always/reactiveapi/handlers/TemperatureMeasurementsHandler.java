package inc.temp.right.always.reactiveapi.handlers;

import inc.temp.right.always.reactiveapi.services.TemperatureMeasurementsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Log4j2
public class TemperatureMeasurementsHandler {
    @Autowired
    private TemperatureMeasurementsService temperatureMeasurementsService;

    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> anomalies() {
        return temperatureMeasurementsService.anomalies();
    }
}
