package demo.coindesk.service;

import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.util.ResponseDto;

import java.util.List;

public interface CoindeskService {
    ResponseDto<CoindeskResponse> create(CoindeskRequest request);
    ResponseDto<CoindeskResponse> update(Long id, CoindeskRequest request);
    ResponseDto<List<CoindeskResponse>> findAll();
    ResponseDto<Void> delete(String code);
}
