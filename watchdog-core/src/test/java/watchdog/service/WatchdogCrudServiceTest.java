package watchdog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchdog.repository.WatchdogRepository;
import watchdog.repository.bean.WatchdogDataEntity;
import watchdog.repository.exception.PersistenceException;
import watchdog.service.bean.WatchDog;
import watchdog.service.converter.WatchdogConverter;
import watchdog.service.exception.WatchdogException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class WatchdogCrudServiceTest extends BaseTest {

    WatchdogCrudService watchdogCrudService;
    private WatchdogRepository watchDogRepositoryMock = mock(WatchdogRepository.class);
    private WatchdogConverter watchDogConverterMock = mock(WatchdogConverter.class);

    @BeforeEach
    void setUp() {
        watchdogCrudService = new WatchdogCrudService(
                watchDogRepositoryMock,
                watchDogConverterMock
        );
    }

    @Test
    void shouldInsertNew() {
        //given
        WatchDog watchDog = randomWatchdog();

        WatchdogDataEntity expectedEntityToInsert = randomWathDogDataEntity();
        WatchDog expectedBeanToReturn = randomWatchdog();
        WatchdogDataEntity expectedInsertedEntity = randomWathDogDataEntity();
        when(watchDogConverterMock.toEntity(any())).thenReturn(expectedEntityToInsert);
        when(watchDogConverterMock.toBean(any())).thenReturn(expectedBeanToReturn);
        when(watchDogRepositoryMock.insert(any())).thenReturn(expectedInsertedEntity);
        //when
        WatchDog result = watchdogCrudService.insert(watchDog);
        //then
        verify(watchDogConverterMock).toEntity(watchDog);
        verify(watchDogRepositoryMock).insert(expectedEntityToInsert);
        verify(watchDogConverterMock).toBean(expectedInsertedEntity);
        assertThat(result)
                .isEqualTo(expectedBeanToReturn);
    }

    @Test
    void shouldThrowExceptionIfPersistenceException() {
        //given
        WatchDog watchDog = randomWatchdog();
        WatchdogDataEntity expectedEntityToInsert = randomWathDogDataEntity();
        when(watchDogConverterMock.toEntity(any())).thenReturn(expectedEntityToInsert);
        when(watchDogRepositoryMock.insert(any())).thenThrow(new PersistenceException());
        //when
        assertThatExceptionOfType(WatchdogException.class)
                .isThrownBy(() -> watchdogCrudService.insert(watchDog));
        //then
        verify(watchDogConverterMock).toEntity(watchDog);
        verify(watchDogRepositoryMock).insert(expectedEntityToInsert);

    }
}