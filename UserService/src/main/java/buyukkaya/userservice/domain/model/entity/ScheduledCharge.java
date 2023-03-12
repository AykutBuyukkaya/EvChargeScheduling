package buyukkaya.userservice.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ScheduledCharge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EV_USER_ID")
    private EVUser evUser;

    @OneToOne
    @JoinColumn(name = "EV_ID")
    private Ev ev;

    @Column(name = "charging_station_id")
    private UUID chargingStationId;

    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime;

    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @Column(name = "chargeStartingTime")
    private LocalDateTime chargeStartingTime;

    @Column(name = "chargeEndingTime")
    private LocalDateTime chargeEndingTime;

    @Column(name = "chargingDuration")
    private Integer chargingDuration;

    @Column(name = "initialSoC")
    private Integer initialSoC;

    @Column(name = "finalSoC")
    private Integer finalSoC;

}
