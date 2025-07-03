import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { federation } from "@module-federation/vite";

export default defineConfig({
  plugins: [
    react(),
    federation({
      name: "loginApp",
      filename: "remoteEntry.js",
      exposes: {
        "./Login": "./src/components/Login.tsx",
      },
      shared: ["react", "react-dom"],
    }),
  ],
  build: {
    target: "esnext",
  },
});