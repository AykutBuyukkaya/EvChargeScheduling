import {ChargeSchedulingRequest, ChargeSchedulingResponse} from "../../shared/ChargeScheduling";
import {Card, Col, Container, Row} from "react-bootstrap";

interface ChargeSchedulingProps {
    data: ChargeSchedulingRequest | ChargeSchedulingResponse;
}

export default function ChargeSchedulingCard(props: ChargeSchedulingProps) {


    return (
        <Card className={"my-4 w-100"} border={"status" in props.data ?
            props.data.status === "SUCCESSFUL" ? "success" : "danger"
            : "info"}>
            <Card.Header>{props.data.vehicleName.replaceAll("undefined", "")}</Card.Header>
            <Card.Body>
                <h5>{props.data.chargingStationName}</h5>
                <Container>
                    <Row>
                        <Col md={{offset: 0}}>Arrival Time: {props.data.arrivalTime}</Col>
                        <Col md={{offset: 6}}>Departure Time: {props.data.departureTime}</Col>
                    </Row>
                    <Row>
                        <Col md={{offset: 0}}>Current SoC: {props.data.currentSoC}</Col>
                        <Col md={{offset: 6}}>Expected SoC: {props.data.expectedSoC}</Col>
                    </Row>
                    {"status" in props.data &&
                        <Container>
                            <Row>
                                <Col md={{offset: 0}}>Charge Start Time: {props.data.chargeStartTime}</Col>
                                <Col md={{offset: 6}}>Charge End Time: {props.data.chargeEndTime}</Col>
                            </Row>
                            <Row>
                                <Col md={{offset: 0}}>Required Time To Charging (In Minutes): {props.data.requiredTimeToChargeInMinutes}</Col>
                                <Col md={{offset: 6}}><b>Priority Score: {props.data.priorityScore}</b></Col>
                            </Row>
                            <Row>
                                <Col>Status:
                                    <p className={props.data.status === "SUCCESSFUL" ? "text-success" : "text-danger"}>{props.data.status}</p>
                                </Col>
                            </Row>
                        </Container>}
                </Container>
            </Card.Body>
        </Card>
    );

}