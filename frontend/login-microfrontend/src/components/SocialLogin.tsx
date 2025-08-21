import { Button, Space } from "antd";
import { GoogleOutlined, FacebookOutlined } from "@ant-design/icons";

const SocialLogin = () => (
  <div className="social-login">
    <span className="social-text">Or login with</span>
    <Space className="social-buttons">
      <Button
        className="social-btn google"
        icon={<GoogleOutlined />}
        size="large"
      >
        Google
      </Button>
      <Button
        className="social-btn facebook"
        icon={<FacebookOutlined />}
        size="large"
      >
        Facebook
      </Button>
    </Space>
  </div>
);

export default SocialLogin;
