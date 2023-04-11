export interface ChargeSchedulingRequest {
    evTypeId: string;
    chargingStationId: string;
    vehicleName: string;
    chargingStationName: string;
    arrivalTime: string;
    departureTime: string;
    currentSoC: string;
    expectedSoC: string;
}

export interface ChargeSchedulingResponse extends ChargeSchedulingRequest{
    chargeStartTime: string;
    chargeEndTime: string;
    chargingPower: string;
    requiredTimeToChargeInMinutes: string;
    priorityScore: string;
    status: string;
}