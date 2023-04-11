export type EvData = {
    id: string;
    brand: string;
    model: string;
    releaseYear: string;
    variant: string;
    usableBatterySize: number;
    acCharger: AcCharger;
    energyConsumption:EnergyConsumption;
}

export type AcCharger = {
    max_power: number;
    power_per_charging_point: Map<string, number>;
}

export type EnergyConsumption = {
    average_consumption: number;
}
