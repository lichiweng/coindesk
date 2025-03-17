package demo.coindesk.service.Impl;

import demo.coindesk.dao.CoindeskDao;
import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.entity.CoindeskEntity;
import demo.coindesk.service.CoindeskService;
import demo.coindesk.util.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private CoindeskDao coindeskDao;

    @Override
    public ResponseDto<CoindeskResponse> create(CoindeskRequest request) {
        // check data is existed?
        // if yes, return fail
        // if no, save db and return response
        return null;
    }

    @Override
    public Long update(Long id, List<CoindeskEntity> entities) {
        return 0L;
    }

    @Override
    public List<CoindeskEntity> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void delete(Long id) {

    }
}
