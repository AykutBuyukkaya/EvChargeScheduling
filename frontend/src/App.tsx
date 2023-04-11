import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, Routes} from "react-router-dom";
import ChargeScheduling from "./pages/ChargeScheduling";
import AppNavbar from "./components/Navbar/AppNavbar";

function App() {
    return (
        <>
            <AppNavbar/>
            <Routes>
                <Route element={<ChargeScheduling/>} path={"/charge-scheduling"}/>
            </Routes>
        </>
    );
}

export default App;
