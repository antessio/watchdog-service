package watchdog.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import watchdog.repository.WatchdogRepository;
import watchdog.repository.bean.WatchdogDataEntity;
import watchdog.service.bean.WatchDog;
import watchdog.service.converter.WatchdogConverter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WatchdogLoadServiceTest extends BaseTest {
    private WatchdogConverter mockedWatchdogConverter = Mockito.mock(WatchdogConverter.class);
    private WatchdogRepository mockedRepository = Mockito.mock(WatchdogRepository.class);
    private WatchdogLoadService loadService;

    @BeforeEach
    void setUp() {
        loadService = new WatchdogLoadService(mockedRepository, mockedWatchdogConverter);
    }

    @Test
    void shouldLoad() {
        //given
        String externalId = UUID.randomUUID().toString();
        Mockito.when(mockedRepository.getByExternalId(ArgumentMatchers.any(), ArgumentMatchers.any(), eq(null)))
                .thenReturn(randomListOf(5, WatchdogDataEntity.class));
        Mockito.when(mockedRepository.getByExternalId(ArgumentMatchers.any(), ArgumentMatchers.any(), notNull()))
                .thenReturn(randomListOf(5, WatchdogDataEntity.class))
                .thenReturn(randomListOf(5, WatchdogDataEntity.class))
                .thenReturn(Collections.emptyList());

        when(mockedWatchdogConverter.toBean(any())).thenReturn(randomWatchdog());
        //when
        Stream<WatchDog> result = loadService.getByExternalId(externalId);
        List<WatchDog> resultList = result.collect(Collectors.toList());
        //then
        verify(mockedRepository).getByExternalId(externalId, 5, null);
        verify(mockedRepository, times(3)).getByExternalId(eq(externalId), eq(5), notNull());
        verify(mockedWatchdogConverter, times(15)).toBean(any());
        assertThat(resultList).hasSize(15);

    }


}