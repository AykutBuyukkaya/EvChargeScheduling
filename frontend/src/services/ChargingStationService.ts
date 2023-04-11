import axios from "axios";

const baseUrl = "http://localhost:1234/charging-station";

export async function GetChargingStations(){
    return await axios.get(baseUrl.concat("/all"));
}