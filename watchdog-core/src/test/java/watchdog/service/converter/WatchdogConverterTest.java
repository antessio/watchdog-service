package watchdog.service.converter;

import antessio.utils.ConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchdog.repository.bean.WatchdogDataEntity;
import watchdog.service.BaseTest;
import watchdog.service.bean.WatchDog;


import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WatchdogConverterTest extends BaseTest {
    private ConverterService mockedConverterService = mock(ConverterService.class);
    WatchdogConverter converter;

    @BeforeEach
    void setUp() {
        converter = new WatchdogConverter(mockedConverterService);
    }


    @Test
    void shouldConvertToBean() {
        //given
        WatchdogDataEntity entity = randomWathDogDataEntity();
        when(mockedConverterService.convert(any(), any())).thenReturn(random(EventExample.class));
        //when
        WatchDog<EventExample> result = converter.toBean(entity, EventExample.class);
        //then
        verify(mockedConverterService).convert(entity.getEvent(), EventExample.class);
        assertThat(result)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldConvertToDataEntity() {
        //given
        WatchDog<EventExample> watchDog = randomWatchdog(EventExample.class);
        when(mockedConverterService.convertToMap(any())).thenReturn(new HashMap<>());
        //when
        WatchdogDataEntity result = converter.toEntity(watchDog);
        //then
        verify(mockedConverterService).convertToMap(any(EventExample.class));
        assertThat(result)
                .hasNoNullFieldsOrPropertiesExcept("statusOrdering", "lock", "lockUntil", "ordering");
    }


}