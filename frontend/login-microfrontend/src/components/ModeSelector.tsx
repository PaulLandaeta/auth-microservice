import React from "react";
import { Button, Space } from "antd";

interface ModeSelectorProps {
  mode: string;
  setMode: React.Dispatch<React.SetStateAction<string>>;
}

const ModeSelector: React.FC<ModeSelectorProps> = ({ mode, setMode }) => (
  <div className="mode-selector">
    <Space>
      <Button
        onClick={() => setMode("left-figure")}
        disabled={mode === "left-figure"}
        className="mode-btn"
      >
        Figura Izquierda
      </Button>
      <Button
        onClick={() => setMode("center")}
        disabled={mode === "center"}
        className="mode-btn"
      >
        Centralizado
      </Button>
      <Button
        onClick={() => setMode("right-figure")}
        disabled={mode === "right-figure"}
        className="mode-btn"
      >
        Figura Derecha
      </Button>
    </Space>
  </div>
);

export default ModeSelector;
