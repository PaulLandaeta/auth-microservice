import axiosInstance from "../api/axiosInstance";

export async function changePassword(email: string, newPassword: string) {
  try {
    const response = await axiosInstance.post("/auth/change-password", {
      email,
      newPassword,
    });
    return response.data;
  } catch (error: any) {
    throw error.response?.data || { message: "Error al cambiar la contraseña" };
  }
}
