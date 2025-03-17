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
    public ResponseDto<CoindeskResponse> create(
            @RequestBody CoindeskRequest request) {
        log.info("Create coindesk request: {}", request);
        return coindeskService.create(request);
    }

    @PutMapping(value = "/{id}")
    public ResponseDto<CoindeskResponse> update(
            @PathVariable Long id,
            @RequestBody CoindeskRequest request
    ) {
        log.info("Update coindesk request: {}", request);
        return coindeskService.update(id, request);
    }

    @GetMapping
    public ResponseDto<List<CoindeskResponse>> findAll() {
        log.info("Find all coindesks");
        return coindeskService.findAll();
    }

    @DeleteMapping(value = "/{code}")
    public ResponseDto<Void> delete(
            @PathVariable String code
    ) {
        log.info("Delete coindesk request: {}", code);
        return coindeskService.delete(code);
    }
}
