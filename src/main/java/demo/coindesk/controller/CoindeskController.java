package demo.coindesk.controller;

import demo.coindesk.dto.request.CoindeskRequest;
import demo.coindesk.dto.response.CoindeskResponse;
import demo.coindesk.service.CoindeskService;
import demo.coindesk.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @PostMapping
    public ResponseDto<CoindeskResponse> create(CoindeskRequest request) {
        log.info("Create coindesk request: {}", request);
        return coindeskService.create(request);
    }

//    @PutMapping(value = "/{id}")
//    public Long update(Long id) {}
//
//    @GetMapping
//    public List<CoindeskEntity> findAll() {}

    @DeleteMapping
    public void delete(Long id) {}
}
