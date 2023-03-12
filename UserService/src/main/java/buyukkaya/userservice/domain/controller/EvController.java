package buyukkaya.userservice.domain.controller;

import buyukkaya.userservice.domain.model.request.AddEvRequest;
import buyukkaya.userservice.domain.model.response.AddEvResponse;
import buyukkaya.userservice.domain.model.response.GetEvResponse;
import buyukkaya.userservice.domain.service.EvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/ev")
public class EvController {

    private final EvService evService;

    public EvController(EvService evService) {
        this.evService = evService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddEvResponse> addEv(@RequestBody AddEvRequest addEvRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AddEvResponse.builder()
                .ev(evService.addEv(addEvRequest))
                .timestamp(Instant.now())
                .message("EV created successfully!")
                .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetEvResponse> getUserEvs(@PathVariable UUID userId) {
        return ResponseEntity.ok(GetEvResponse.builder()
                .evList(evService.getUserEVs(userId))
                .message("Users EVs fetch successfully.")
                .timestamp(Instant.now())
                .build());
    }

}
