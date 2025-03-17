package demo.coindesk.service.Impl;

import demo.coindesk.dao.CoindeskDao;
import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.entity.CoindeskEntity;
import demo.coindesk.service.CoindeskService;
import demo.coindesk.util.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private CoindeskDao coindeskDao;

    @Override
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
    public ResponseDto<Void> delete(String code) {

        Optional<CoindeskEntity> coindeskOpt = coindeskDao.findByCode(code);
        if (!coindeskOpt.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add(code + " is not exists.");
            return ResponseDto.<Void>fail(errors);
        }

        coindeskDao.delete(coindeskOpt.get());
        return ResponseDto.<Void>success(null);
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
