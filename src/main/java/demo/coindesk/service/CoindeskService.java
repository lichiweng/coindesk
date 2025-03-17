package demo.coindesk.service;

import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.entity.CoindeskEntity;
import demo.coindesk.util.ResponseDto;

import java.util.List;

public interface CoindeskService {
    ResponseDto<CoindeskResponse> create(CoindeskRequest request);
    Long update(Long id, List<CoindeskEntity> entities);
    List<CoindeskEntity> findAll();
    void delete(Long id);
}
