import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";

export default function AppNavbar() {
    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="charge-scheduling">EV Charge Scheduling</Navbar.Brand>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ms-auto">
                        <Nav.Link href="charge-scheduling">Charge Scheduling</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}