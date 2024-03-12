package inc.temp.right.always.reactiveapi;

import inc.temp.right.always.reactiveapi.controllers.TemperatureMeasurementsController;
import inc.temp.right.always.reactiveapi.services.TemperatureMeasurementsService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

class ReactiveApiApplicationTests {
	@Test
	void TemperatureMeasurementsController_anomalies_Test() {
		TemperatureMeasurementsController temperatureMeasurementsController = new TemperatureMeasurementsController();
		TemperatureMeasurementsService temperatureMeasurementsService = mock(TemperatureMeasurementsService.class);

		Flux<String> expectedResult = Flux.just("anomaly1", "anomaly2", "anomaly3");

		when(temperatureMeasurementsService.anomalies()).thenReturn(Flux.just("anomaly1", "anomaly2", "anomaly3"));

		temperatureMeasurementsController.setTemperatureMeasurementsService(temperatureMeasurementsService);

		Flux<String> result = temperatureMeasurementsController.anomalies();

		verify(temperatureMeasurementsService, times(1)).anomalies();

		assertIterableEquals(expectedResult.toIterable(), result.toIterable(), String.format("Expected result: %s and returned result: %s are not the same when returning stream of anomalies.", expectedResult.toIterable(), result.toIterable()));
	}
}
