
import { Form } from "antd";
import Input from "antd/es/input/Input";
import { Button } from "antd";
import AntdLink from "antd/es/typography/Link";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
  const navigate = useNavigate();

  const onFinish = (values: any) => {
    // Si ambos campos tienen valor, redirige
    if (values.email && values.password) {
      navigate("/success");
    }
  };

  return (
    <Form className="login-form" onFinish={onFinish}>
      <Form.Item
        name="email"
        rules={[{ required: true, message: "Please input your email!" }]}
      >
        <Input type="email" placeholder="Email" />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[{ required: true, message: "Please input your password!" }]}
      >
        <Input type="password" placeholder="Password" />
      </Form.Item>

      <Form.Item>
        <div style={{ textAlign: "right" }}>
          <AntdLink href="/forgot-password">Forgot Password?</AntdLink>
        </div>
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" block>
          LOGIN
        </Button>
      </Form.Item>
    </Form>
  );
};

export default LoginForm;
