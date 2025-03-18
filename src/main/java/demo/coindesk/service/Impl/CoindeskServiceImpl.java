package demo.coindesk.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.coindesk.dao.CoindeskDao;
import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.*;
import demo.coindesk.entity.CoindeskEntity;
import demo.coindesk.enums.CurrencyEnum;
import demo.coindesk.service.CoindeskService;
import demo.coindesk.util.CoindeskHelper;
import demo.coindesk.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private CoindeskDao coindeskDao;
    @Autowired
    private CoindeskHelper coindeskHelper;

    @Autowired
    public CoindeskServiceImpl(CoindeskDao coindeskDao, CoindeskHelper coindeskHelper) {
        this.coindeskDao = coindeskDao;
        this.coindeskHelper = coindeskHelper;
    }

    @Override
    @Transactional
    public ResponseDto<CoindeskResponse> create(CoindeskRequest request) {

        Optional<CoindeskEntity> coindeskOpt = coindeskDao.findByCodeAndCharName(
                request.getCode(), request.getCharName());
        if (coindeskOpt.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add(request.getCurrencyEn() + " already exists.");
            return ResponseDto.<CoindeskResponse>fail(errors);
        }

        CoindeskEntity coindeskEntity = generateCoindeskEntity(request);
        coindeskEntity = coindeskDao.save(coindeskEntity);

        return ResponseDto.<CoindeskResponse>success(CoindeskResponse.generate(coindeskEntity));
    }

    @Override
    @Transactional
    public ResponseDto<CoindeskResponse> update(Long id, CoindeskRequest request) {

        Optional<CoindeskEntity> coindeskOpt = coindeskDao.findById(id);
        if (!coindeskOpt.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add(request.getCurrencyEn() + " is not exists.");
            return ResponseDto.<CoindeskResponse>fail(errors);
        }

        LocalDateTime now = LocalDateTime.now();
        CoindeskEntity coindeskEntity = coindeskOpt.get();
        coindeskEntity.setCharName(request.getCharName());
        coindeskEntity.setUpdatedUtc(request.getUpdatedUtc());
        coindeskEntity.setCode(request.getCode());
        coindeskEntity.setCurrencyEn(request.getCurrencyEn());
        coindeskEntity.setCurrencyCh(request.getCurrencyCh());
        coindeskEntity.setSymbol(request.getSymbol());
        coindeskEntity.setRate(request.getRate());
        coindeskEntity.setDescription(request.getDescription());
        coindeskEntity.setRateFloat(request.getRateFloat());
        coindeskEntity.setCreatedBy("system");
        coindeskEntity.setCreatedDate(now);
        coindeskEntity.setUpdatedBy("system");
        coindeskEntity.setUpdatedDate(now);
        coindeskEntity = coindeskDao.save(coindeskEntity);

        return ResponseDto.<CoindeskResponse>success(CoindeskResponse.generate(coindeskEntity));
    }

    @Override
    @Transactional
    public ResponseDto<List<CoindeskResponse>> findAll() {

        List<CoindeskEntity> coindeskEntities = coindeskDao.findAll();
        if (coindeskEntities.isEmpty()) {
            List<String> errors = new ArrayList<>();
            errors.add("no data");
            return ResponseDto.<List<CoindeskResponse>>fail(errors);
        }

        List<CoindeskResponse> coindeskResponses = coindeskEntities.stream()
                .map(CoindeskResponse::generate)
                .collect(Collectors.toList());

        return ResponseDto.<List<CoindeskResponse>>success(coindeskResponses);
    }

    @Override
    @Transactional
    public ResponseDto<Void> delete(String code) {

        Optional<CoindeskEntity> coindeskOpt = coindeskDao.findByCode(code);
        if (!coindeskOpt.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add(code + " is not exists.");
            return ResponseDto.<Void>fail(errors);
        }

        coindeskDao.deleteById(coindeskOpt.get().getId());
        return ResponseDto.<Void>success(null);
    }

    @Override
    public ResponseDto<GetOtherCoindeskResponse> getOtherCoindesk() {

        OtherCoindeskResponse otherCoindeskResponse = coindeskHelper.request();
        GetOtherCoindeskResponse getOtherCoindeskResponse = new GetOtherCoindeskResponse();
        getOtherCoindeskResponse.setUpdateDate(otherCoindeskResponse.getTime().getUpdated());
        Map<String, CurrencyResponse> bpiMap = otherCoindeskResponse.getBpi();
        List<OtherCurrencyResponse> otherCurrencyList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Map.Entry<String, CurrencyResponse> entry : bpiMap.entrySet()) {
            CurrencyResponse currencyResponse = objectMapper.convertValue(entry.getValue(), CurrencyResponse.class);
            OtherCurrencyResponse otherCurrencyResponse = new OtherCurrencyResponse();
            otherCurrencyResponse.setCodeEn(currencyResponse.getCode());
            otherCurrencyResponse.setSymbol(StringEscapeUtils.unescapeHtml4(currencyResponse.getSymbol()));
            otherCurrencyResponse.setCodeCh(CurrencyEnum.getNameByCode(currencyResponse.getCode()));
            otherCurrencyList.add(otherCurrencyResponse);
        }
        getOtherCoindeskResponse.setCurrencyList(otherCurrencyList);
        return ResponseDto.<GetOtherCoindeskResponse>success(getOtherCoindeskResponse);
    }

    private CoindeskEntity generateCoindeskEntity(CoindeskRequest request) {
        LocalDateTime now = LocalDateTime.now();
        CoindeskEntity coindeskEntity = new CoindeskEntity();
        coindeskEntity.setCharName(request.getCharName());
        coindeskEntity.setUpdatedUtc(request.getUpdatedUtc());
        coindeskEntity.setCode(request.getCode());
        coindeskEntity.setCurrencyEn(request.getCurrencyEn());
        coindeskEntity.setCurrencyCh(request.getCurrencyCh());
        coindeskEntity.setSymbol(request.getSymbol());
        coindeskEntity.setRate(request.getRate());
        coindeskEntity.setDescription(request.getDescription());
        coindeskEntity.setRateFloat(request.getRateFloat());
        coindeskEntity.setCreatedBy("system");
        coindeskEntity.setCreatedDate(now);
        coindeskEntity.setUpdatedBy("system");
        coindeskEntity.setUpdatedDate(now);
        return coindeskEntity;
    }
}
