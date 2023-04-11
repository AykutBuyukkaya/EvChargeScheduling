import React, {MouseEventHandler, useEffect, useState} from "react";
import {Alert, Button, Col, Container, Form, FormGroup, Modal, Row} from "react-bootstrap";
import {BrandData} from "../../shared/BrandData";
import {EvData} from "../../shared/EvData";
import {ChargingStationData} from "../../shared/ChargingStationData";
import {GetBrandData, GetEvDataByBrandId} from "../../services/EvDataService";
import {GetChargingStations} from "../../services/ChargingStationService";
import Select from "../Select/Select";
import AcChargingPowerTable from "../Table/AcChargingPowerTable";
import {ChargeSchedulingRequest} from "../../shared/ChargeScheduling";

export interface AddEvModalProps {
    isOpen: boolean;
    onClose: MouseEventHandler;
    onSave: Function;
}

export default function AddEvModal(props: AddEvModalProps) {

    const [brandDataList, setBrandDataList] = useState<BrandData[]>([]);
    const [evDataList, setEvDataList] = useState<EvData[]>([]);
    const [isBrandSelected, setBrandSelected] = useState<boolean>(false);
    const [selectedEv, setSelectedEv] = useState<EvData>();
    const [chargingStationDataList, setChargingStationDataList] = useState<ChargingStationData[]>([]);
    const [selectedChargingStation, setSelectedChargingStation] = useState<ChargingStationData>();
    const [modalRender, setModalRender] = useState(true);
    const [alertOpen, setAlertOpen] = useState(false);
    const [chargeSchedulingRequest, setChargeSchedulingRequest] = useState<ChargeSchedulingRequest>({
        arrivalTime: "",
        chargingStationId: "",
        currentSoC: "",
        departureTime: "",
        evTypeId: "",
        expectedSoC: "",
        chargingStationName: "",
        vehicleName: ""
    });


    useEffect(() => {
        GetBrandData().then(value => setBrandDataList(value.data.brandList))
        GetChargingStations().then(value => setChargingStationDataList(value.data.chargingStationList));
        setEvDataList([]);
        setBrandSelected(false);
        setSelectedEv(undefined)
        setSelectedChargingStation(undefined)
        setChargeSchedulingRequest({
            arrivalTime: "",
            chargingStationId: "",
            currentSoC: "",
            departureTime: "",
            evTypeId: "",
            expectedSoC: "",
            chargingStationName: "",
            vehicleName: ""
        })
        setModalRender(false);
    }, [modalRender])

    function handeBrandSelect(brandId: string) {
        GetEvDataByBrandId(brandId).then(value => setEvDataList(value.data.evList));
        setBrandSelected(true);
    }

    function handleEvSelect(evId: string) {
        setSelectedEv(evDataList.find(value => value.id === evId));
    }

    function handleChargingStationSelect(chargingStationId: string) {
        setSelectedChargingStation(chargingStationDataList.find(value => value.id === chargingStationId))

    }

    function handleSave() {
        if (selectedEv?.id !== undefined) {
            chargeSchedulingRequest.evTypeId = selectedEv.id;
            chargeSchedulingRequest.vehicleName = selectedEv.brand.concat(" ").concat(selectedEv.model).concat(" ").concat(selectedEv.variant).concat(" ").concat(selectedEv.releaseYear);
        }
        if (selectedChargingStation?.id !== undefined) {
            chargeSchedulingRequest.chargingStationId = selectedChargingStation.id;
            chargeSchedulingRequest.chargingStationName = selectedChargingStation.name;
        }

        setModalRender(true);
        setAlertOpen(true)
        props.onSave(chargeSchedulingRequest);

    }


    return (
        <>
            <Alert className={"m-3 w-25"} style={{zIndex:99999}} variant={"success"} show={alertOpen} dismissible={true} onClose={() => setAlertOpen(false)}>
                <Alert.Heading>Request Successfully Added</Alert.Heading>
                <p>Charge Scheduling Request added successfully!</p>
            </Alert>
            <Modal show={props.isOpen} onHide={() => props.onClose}>
                <Modal.Header>
                    <Modal.Title>
                        Add New Ev For Charge Scheduling
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className={"my-3"}>
                            <Form.Label>Select Brand</Form.Label>
                            <Select selectDataList={brandDataList.map(value => ({value: value.id, label: value.name}))}
                                    onValueChange={handeBrandSelect}/>
                        </Form.Group>
                        <Form.Group className={"my-3"}>
                            <Form.Label>Select EV</Form.Label>
                            <Select selectDataList={evDataList.map(value => ({
                                label: value.brand.concat(" ").concat(value.model).concat(" ").concat(value.variant).concat(" ").concat(value.releaseYear === undefined ? " " : value.releaseYear),
                                value: value.id
                            }))} onValueChange={handleEvSelect} isDisabled={!isBrandSelected}/>
                        </Form.Group>
                        {selectedEv !== undefined &&
                            <Container className={"border border-2 py-3"}>
                                <Row className={"py-3"}>
                                    <h3>{selectedEv?.brand} {selectedEv?.model} {selectedEv?.variant} {selectedEv?.releaseYear}</h3>
                                </Row>
                                <Row className={"py-3"}>
                                    <Col md={{offset: 0, span: 4}}>
                                        <Form.Group>
                                            <Form.Label>Battery Size</Form.Label>
                                            <Form.Control readOnly={true} disabled={true}
                                                          value={selectedEv?.usableBatterySize}></Form.Control>
                                        </Form.Group>
                                    </Col>
                                    <Col md={{offset: 4, span: 4}}>
                                        <Form.Group>
                                            <Form.Label>Avg Consumption</Form.Label>
                                            <Form.Control readOnly={true} disabled={true}
                                                          value={selectedEv?.energyConsumption.average_consumption}></Form.Control>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row className={"py-3"}>
                                    <Col><h4>Ac Charging Capacities</h4></Col>
                                </Row>
                                <Row>
                                    <Col md={{offset: 0, span: 4}}>
                                        <FormGroup>
                                            <Form.Label>Max Charging Power</Form.Label>
                                            <Form.Control readOnly={true} disabled={true}
                                                          value={selectedEv?.acCharger.max_power}></Form.Control>
                                        </FormGroup>
                                    </Col>
                                </Row>
                                <Row className={"py-3"}>
                                    <Col><h4>Charging power per charging point</h4></Col>
                                </Row>
                                <AcChargingPowerTable evData={selectedEv}/>
                            </Container>}
                        <Form.Group className={"my-3"}>
                            <Form.Label>Arrival Time</Form.Label>
                            <Form.Control type={"time"}
                                          onChange={event => chargeSchedulingRequest.arrivalTime = event.currentTarget.value}/>
                        </Form.Group>
                        <Form.Group className={"my-3"}>
                            <Form.Label>Departure Time</Form.Label>
                            <Form.Control type={"time"}
                                          onChange={event => chargeSchedulingRequest.departureTime = event.currentTarget.value}/>
                        </Form.Group>
                        <Row className={"my-3"}>
                            <Col>
                                <Form.Group className={"w-75"}>
                                    <Form.Label> Current SoC </Form.Label>
                                    <Form.Control type={"number"} min={0.0} max={100}
                                                  onChange={event => chargeSchedulingRequest.currentSoC = event.currentTarget.value}></Form.Control>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className={"w-75"}>
                                    <Form.Label> Expected SoC </Form.Label>
                                    <Form.Control type={"number"} min={0.0} max={100}
                                                  onChange={event => chargeSchedulingRequest.expectedSoC = event.currentTarget.value}></Form.Control>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Form.Group className={"my-3"}>
                            <Form.Label>Select Charging Station</Form.Label>
                            <Select selectDataList={chargingStationDataList.map(value => ({
                                value: value.id,
                                label: value.name
                            }))}
                                    onValueChange={handleChargingStationSelect}/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant={"outline-danger"} onClick={props.onClose}>Close</Button>
                    <Button variant={"success"} onClick={() => handleSave()}>Save</Button>
                </Modal.Footer>
            </Modal>
        </>
    )

}