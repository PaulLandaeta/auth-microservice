import { useState } from "react";
import Login from "./components/Login";
import ModeSelector from "./components/ModeSelector";
import "./App.css";

function App() {
  const [mode, setMode] = useState("center");

  return (
    <div className="background-full">
      <div className="app-container">
        <ModeSelector mode={mode} setMode={setMode} />
        <Login mode={mode} />
      </div>
    </div>
  );
}

export default App;

