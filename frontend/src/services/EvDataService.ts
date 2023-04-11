import axios from "axios";

//TODO MOVE THIS TO CONSTANTS CLASS
const baseUrl = "http://localhost:1234/ev-data"

//TODO ADD CUSTOM TYPE RESPONSES
export async function GetBrandData() {
    return await axios.get(baseUrl.concat("/brand/all"));
}

export async function GetEvDataByBrandId(brandId?: string) {

    return await axios.get(baseUrl.concat("/ev/all"), {params: {brandId: brandId}})
}
