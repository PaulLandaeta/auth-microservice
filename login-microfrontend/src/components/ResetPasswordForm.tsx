import { useState } from "react";
import { Form, Input, message } from "antd";
import { useNavigate } from "react-router-dom";
import { changePassword } from "../services/authService";

const passwordPattern = /(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}/;

export default function ResetPasswordForm() {
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();
  const navigate = useNavigate();

  const onFinish = async (values: { email: string; newPassword: string; confirmPassword: string }) => {
    setLoading(true);
    try {
      await changePassword(values.email, values.newPassword);
      message.success("Contraseña actualizada correctamente");
      form.resetFields();
      navigate("/reset-confirmation");
    } catch (err: any) {
      message.error(err?.message || "Ocurrió un error. Intenta nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="reset-password-form">
      <h3>Restablecer contraseña</h3>
      <Form form={form} layout="vertical" onFinish={onFinish}>
        <Form.Item
          label="Correo electrónico"
          name="email"
          rules={[{ required: true, message: "Por favor ingresa tu correo" }, { type: "email", message: "Correo inválido" }]}
        >
          <Input placeholder="ejemplo@correo.com" size="large" />
        </Form.Item>

        <Form.Item
          label="Nueva contraseña"
          name="newPassword"
          rules={[
            { required: true, message: "Por favor ingresa tu nueva contraseña" },
            { pattern: passwordPattern, message: "Mínimo 8 caracteres, al menos 1 mayúscula, 1 minúscula y 1 número" },
          ]}
          hasFeedback
        >
          <Input.Password placeholder="Nueva contraseña" size="large" autoFocus />
        </Form.Item>

        <Form.Item
          label="Confirmar contraseña"
          name="confirmPassword"
          dependencies={["newPassword"]}
          hasFeedback
          rules={[
            { required: true, message: "Por favor confirma tu contraseña" },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("newPassword") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error("Las contraseñas no coinciden"));
              },
            }),
          ]}
        >
          <Input.Password placeholder="Confirmar contraseña" size="large" />
        </Form.Item>

        <div style={{ display: "flex", justifyContent: "flex-end" }}>
          <button
            type="button"
            onClick={() => form.submit()}
            className="reset-password-button"
            disabled={loading}
          >
            {loading ? "Guardando..." : "Reset Password"}
          </button>
        </div>
      </Form>
    </div>
  );
}
