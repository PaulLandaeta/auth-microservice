import React from "react";

interface ModeSelectorProps {
  mode: string;
  setMode: React.Dispatch<React.SetStateAction<string>>;
}

const ModeSelector: React.FC<ModeSelectorProps> = ({ mode, setMode }) => (
  <div className="mode-selector">
    <button onClick={() => setMode("left-figure")} disabled={mode === "left-figure"}>
      Figura Izquierda
    </button>
    <button onClick={() => setMode("center")} disabled={mode === "center"}>
      Centralizado
    </button>
    <button onClick={() => setMode("right-figure")} disabled={mode === "right-figure"}>
      Figura Derecha
    </button>
  </div>
);

export default ModeSelector;
