import {ChargeSchedulingRequest} from "../shared/ChargeScheduling";
import axios from "axios";

const baseUrl = "http://localhost:1234/charge-scheduling/schedule";

export async function SendChargingRequests(chargeSchedulingRequestList:ChargeSchedulingRequest[]){

    const data = JSON.stringify(chargeSchedulingRequestList);
    const config = {
        headers: {'Content-Type': 'application/json'}
    }

    console.log(chargeSchedulingRequestList)
    return axios.post(baseUrl.concat("/peak-shaving"),data, config);

}
