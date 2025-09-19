import { useState } from "react";
import { useAuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import { object, string } from "yup";
import type { InferType } from "yup";
import { Form, Input, Typography, message as antdMessage } from "antd";
import SocialLoginButtons from "./SocialLoginButtons";
import PrimaryButton from "./PrimaryButton";

const { Text } = Typography;

const signInSchema = object({
  email: string().required("Email requerido").email("Email inválido"),
  password: string().required("Contraseña requerida"),
});
type SignInFormData = InferType<typeof signInSchema>;

export default function SignInForm() {
  const navigate = useNavigate();
  const [form] = Form.useForm<SignInFormData>();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const { login, isLoading } = useAuthContext();

  const [messageApi, contextHolder] = antdMessage.useMessage();

  const onFinish = async (values: SignInFormData) => {
    setIsSubmitting(true);
    try {
      await signInSchema.validate(values, { abortEarly: false });

      const data = await login({
        email: values.email,
        password: values.password,
      });

      messageApi.success(data.msg || "Has iniciado sesión correctamente");

      navigate("/");
    } catch (error: unknown) {
      if (error && typeof error === "object" && "inner" in error) {
        const validationError = error as {
          inner?: Array<{ path?: string; message: string }>;
        };
        const fieldErrors: Record<string, string[]> = {};
        validationError.inner?.forEach((err) => {
          if (err.path) fieldErrors[err.path] = [err.message];
        });
        form.setFields(
          Object.entries(fieldErrors).map(([name, errors]) => ({
            name: name as keyof SignInFormData,
            errors,
          }))
        );
      } else {
        const errorMessage =
          error instanceof Error ? error.message : "No se pudo iniciar sesión";
        messageApi.error(errorMessage);
        form.setFields([
          { name: "email", errors: [" "] },
          { name: "password", errors: [" "] },
        ]);
      }
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div
      style={{
        backgroundColor: "#fff",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        flexDirection: "column",
        padding: "0 50px",
        height: "100%",
        textAlign: "center",
      }}
    >
      {contextHolder}

      <h1 style={{ fontWeight: "bold", margin: 0 }}>Sign in</h1>
      <SocialLoginButtons />
      <Text style={{ fontSize: 12 }}>or use your account</Text>

      <Form
        form={form}
        layout="vertical"
        onFinish={onFinish}
        style={{ width: "100%", marginTop: 16 }}
      >
        <Form.Item name="email">
          <Input placeholder="Email" type="email" size="large" />
        </Form.Item>

        <Form.Item name="password">
          <Input.Password placeholder="Password" size="large" />
        </Form.Item>

        <PrimaryButton
          htmlType="submit"
          disabled={isSubmitting || isLoading}
          style={{
            opacity: isSubmitting || isLoading ? 0.7 : 1,
            cursor: isSubmitting || isLoading ? "not-allowed" : "pointer",
          }}
        >
          {isSubmitting || isLoading ? "Signing In..." : "Sign In"}
        </PrimaryButton>
      </Form>

      <button
        type="button"
        onClick={() => navigate("/signup")}
        style={{
          background: "#fff",
          color: "#2563eb",
          border: "1px solid #2563eb",
          borderRadius: 6,
          padding: "8px 20px",
          fontWeight: 500,
          cursor: isSubmitting ? "not-allowed" : "pointer",
          marginTop: 12,
        }}
        disabled={isSubmitting}
      >
        SIGN UP
      </button>
    </div>
  );
}
