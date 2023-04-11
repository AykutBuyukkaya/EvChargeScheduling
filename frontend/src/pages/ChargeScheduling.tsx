import {Button, ButtonGroup, Container, Row} from "react-bootstrap";
import React, {useState} from "react";
import AddEvModal from "../components/Modal/AddEvModal";
import {ChargeSchedulingRequest, ChargeSchedulingResponse} from "../shared/ChargeScheduling";
import ChargeSchedulingCard from "../components/Card/ChargeSchedulingCard";
import {SendChargingRequests} from "../services/ChargeSchedulingService";

export default function ChargeScheduling() {

    const [isEvModalOpen, setEvModalOpen] = useState(false);
    const [isChargeSchedulingComplete, setChargeSchedulingComplete] = useState(false);
    const [chargeSchedulingRequestList, setChargeSchedulingRequestList] = useState<ChargeSchedulingRequest[]>([]);
    const [chargeSchedulingResponseList, setChargeSchedulingResponseList] = useState<ChargeSchedulingResponse[]>([]);

    function handleModalClose() {
        setEvModalOpen(false);
    }

    function handeSendToServer() {
        SendChargingRequests(chargeSchedulingRequestList).then(value => {
            setChargeSchedulingResponseList(value.data.scheduledChargeList)
        });
        setChargeSchedulingComplete(true);
        console.log(chargeSchedulingResponseList)
    }

    function handleChargeSchedulingRequestSave(chargeSchedulingRequest: ChargeSchedulingRequest) {
        chargeSchedulingRequestList.push(chargeSchedulingRequest);
        setEvModalOpen(false)
    }

    //TODO: ADD RESET BUTTON TO RESET TRANSFORMER POWER USAGE.
    return (<>
        <AddEvModal isOpen={isEvModalOpen} onClose={handleModalClose} onSave={handleChargeSchedulingRequestSave}/>
        <Container className={"my-5"}>
            <Row>
                <ButtonGroup>
                    <Button className={"w-50"} onClick={() => setEvModalOpen(true)}>ADD NEW EV</Button>
                    <Button className={"w-50"}>Reset</Button>
                    <Button className={"w-50"} onClick={handeSendToServer}>SEND TO SERVICE</Button>
                </ButtonGroup>

            </Row>
        </Container>
        <Container>
            {(isChargeSchedulingComplete ? chargeSchedulingResponseList : chargeSchedulingRequestList)
                .map(value => <ChargeSchedulingCard data={value}/>)
            }
        </Container>


    </>);

}