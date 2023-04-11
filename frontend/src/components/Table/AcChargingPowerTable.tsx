import {EvData} from "../../shared/EvData";
import {Table} from "react-bootstrap";

interface AcChargingPowerTableProps {
    evData?: EvData;
}

export default function AcChargingPowerTable(props: AcChargingPowerTableProps) {

    const powerPerChargingPointList = props.evData?.acCharger.power_per_charging_point === undefined ? [] : Array.from(Object.entries(props.evData.acCharger.power_per_charging_point));

    return (<>
        <Table>
            <tbody>
            {
                powerPerChargingPointList.map(([key, value]) => (
                    <tr key={key}>
                        <th>{key}</th>
                        <th>{value}</th>
                    </tr>
                ))
            }
            </tbody>
        </Table>

    </>);

}