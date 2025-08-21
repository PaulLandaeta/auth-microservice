import { ConfigProvider, theme as antdTheme, type ThemeConfig } from 'antd';
import type { ReactNode } from 'react';

type ThemeProps = {
  children: ReactNode;
};

const themeConfig: ThemeConfig = {
  algorithm: antdTheme.defaultAlgorithm, 
  token: {
    colorPrimary: '#1890ff', 
    fontFamily: 'Inter, sans-serif', 
  },
};

export const Theme = ({ children }: ThemeProps) => {
  return (
    <ConfigProvider theme={themeConfig}>
      {children}
    </ConfigProvider>
  );
};
