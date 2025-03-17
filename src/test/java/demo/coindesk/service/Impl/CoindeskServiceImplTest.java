package demo.coindesk.service.Impl;

import demo.coindesk.dao.CoindeskDao;
import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.entity.CoindeskEntity;
import demo.coindesk.enums.StatusCodeEnum;
import demo.coindesk.util.CoindeskHelper;
import demo.coindesk.util.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CoindeskServiceImplTest {

    private CoindeskServiceImpl coindeskServiceImpl;
    @Mock
    private CoindeskDao coindeskDao;
    @Mock
    private CoindeskHelper coindeskHelper;

    CoindeskEntity coindeskEntity = new CoindeskEntity();

    @BeforeEach
    void init() {

        coindeskServiceImpl = new CoindeskServiceImpl(coindeskDao, coindeskHelper);
        LocalDateTime now = LocalDateTime.now();
        coindeskEntity.setId(1L);
        coindeskEntity.setCode("testCode");
        coindeskEntity.setCharName("testCharName");
        coindeskEntity.setCurrencyEn("testCurrencyEn");
        coindeskEntity.setCurrencyCh("testCurrencyCh");
        coindeskEntity.setSymbol("testSymbol");
        coindeskEntity.setRate("1,245.8");
        coindeskEntity.setDescription("testDescription");
        coindeskEntity.setRateFloat(BigDecimal.valueOf(1245.8));
        coindeskEntity.setUpdatedUtc("test");
        coindeskEntity.setTimezone("testTimezone");
        coindeskEntity.setCreatedBy("testSystem");
        coindeskEntity.setCreatedDate(now);
        coindeskEntity.setUpdatedBy("testSystem");
        coindeskEntity.setUpdatedDate(now);
    }

    @Test
    void create_success() {

        CoindeskRequest request = new CoindeskRequest();
        request.setCharName("testCharName");
        request.setUpdatedUtc("testUpdatedUtc");
        request.setTimezone("testTimezone");
        request.setCode("testCode");
        request.setCurrencyEn("testCurrencyEn");
        request.setCurrencyCh("testCurrencyCh");
        request.setSymbol("testSymbol");
        request.setRate("1,245.8");
        request.setDescription("testDescription");
        request.setRateFloat(BigDecimal.valueOf(1245.8));

        when(coindeskDao.findByCodeAndCharName(request.getCode(), request.getCharName())).thenReturn(Optional.empty());
        when(coindeskDao.save(any(CoindeskEntity.class))).thenReturn(coindeskEntity);

        ResponseDto<CoindeskResponse> coindeskResponse = coindeskServiceImpl.create(request);
        assertThat(coindeskResponse.getStatus()).isEqualTo(StatusCodeEnum.SUCCESS.name());
        assertThat(coindeskResponse.getData()).isEqualTo(CoindeskResponse.generate(coindeskEntity));

    }

    @Test
    void update_success() {

        when(coindeskDao.findById(any(Long.class))).thenReturn(Optional.of(coindeskEntity));
        when(coindeskDao.save(any(CoindeskEntity.class))).thenReturn(coindeskEntity);

        CoindeskRequest request = new CoindeskRequest();
        request.setCode("testCode");
        request.setCharName("testCharName");
        request.setCurrencyEn("testCurrencyEn");
        request.setCurrencyCh("updatedCurrencyCh");
        request.setSymbol("testSymbol");
        request.setRate("1,245.8");
        request.setDescription("testDescription");
        request.setRateFloat(BigDecimal.valueOf(1245.8));
        request.setUpdatedUtc("test");
        request.setTimezone("testTimezone");

        ResponseDto<CoindeskResponse> coindeskResponse = coindeskServiceImpl.update(1L, request);
        assertThat(coindeskResponse.getStatus()).isEqualTo(StatusCodeEnum.SUCCESS.name());
        assertThat(coindeskResponse.getData()).isEqualTo(CoindeskResponse.generate(coindeskEntity));

    }

    @Test
    void findAll_success() {

        when(coindeskDao.findAll()).thenReturn(Arrays.asList(coindeskEntity));
        ResponseDto<List<CoindeskResponse>> coindeskResponse = coindeskServiceImpl.findAll();
        assertThat(coindeskResponse.getStatus()).isEqualTo(StatusCodeEnum.SUCCESS.name());
        assertThat(coindeskResponse.getData()).isEqualTo(Arrays.asList(CoindeskResponse.generate(coindeskEntity)));
    }

    @Test
    void delete_success() {

        String code = "testCode";
        when(coindeskDao.findByCode(anyString())).thenReturn(Optional.of(coindeskEntity));
        ResponseDto<Void> coindeskResponse = coindeskServiceImpl.delete(code);
        assertThat(coindeskResponse.getStatus()).isEqualTo(StatusCodeEnum.SUCCESS.name());
        verify(coindeskDao).deleteById(coindeskEntity.getId());
    }

}
