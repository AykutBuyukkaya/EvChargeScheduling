package com.buyukkaya.chargeschedulingservice.domain.repository;

import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledChargeRepository extends JpaRepository<ScheduledCharge, UUID> {
}
