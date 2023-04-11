import {Form} from "react-bootstrap";

export type SelectData = {
    label: string
    value: string;
}

export interface SelectProps {
    selectDataList: SelectData[];
    onValueChange: Function;
    isDisabled?: boolean;
}

export default function Select(props: SelectProps) {


    return (
        <Form.Select disabled={props.isDisabled} onChange={event => props.onValueChange(event.target.value)} defaultValue={"qweqeq"}>
            {props.selectDataList.map(value => {
                return <option key={value.value} value={value.value}>{value.label}</option>
            })}
        </Form.Select>
    );

}