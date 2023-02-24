package com.buyukkaya.evdataservice.domain.controller;

import com.buyukkaya.evdataservice.domain.model.exception.NoDataFoundException;
import com.buyukkaya.evdataservice.domain.model.response.ErrorResponse;
import com.buyukkaya.evdataservice.domain.model.response.GetEvResponse;
import com.buyukkaya.evdataservice.domain.service.EvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/ev")
public class EvController {

    private final EvService evService;

    public EvController(EvService evService) {
        this.evService = evService;
    }

    @GetMapping("/all")
    public ResponseEntity<GetEvResponse> getEvListByBrandId(@RequestParam String brandId) {
        return ResponseEntity.ok(GetEvResponse.builder()
                .evList(evService.findAllByBrandId(brandId))
                .timestamp(Instant.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<GetEvResponse> getEvResponseById(@RequestParam String id) {
        return ResponseEntity.ok(GetEvResponse.builder()
                .ev(evService.findById(id))
                .timestamp(Instant.now())
                .build());
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoDataFoundException(Exception e) {
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .message("EV data is not found!")
                .timestamp(Instant.now())
                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        log.error("Exception ", e);
        return ResponseEntity.internalServerError().body(ErrorResponse.builder()
                .message("An Error occurred!")
                .timestamp(Instant.now())
                .build());
    }

}
