package upb.edu.AuthMicroservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.models.LogoutRequest;
import upb.edu.AuthMicroservice.services.SessionService;
import upb.edu.AuthMicroservice.dtos.RefreshTokenRequest;
import upb.edu.AuthMicroservice.exceptions.InvalidRefreshTokenException;
import upb.edu.AuthMicroservice.exceptions.InvalidSessionException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ServerResponse generateSession(ServerRequest request) {
        Map<String, Object> json;
        try {
            json = request.body(Map.class);
        } catch (Exception e) {
            logger.warn("Payload inválido en /generate-session: {}", e.getMessage());
            return ServerResponse
                    .badRequest()
                    .body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        // Extraer user_id del JSON de forma segura
        Integer userId;
        try {
            Object rawUserId = json.get("user_id");
            if (rawUserId == null) {
                throw new IllegalArgumentException("Campo 'user_id' requerido");
            }
            if (rawUserId instanceof Number) {
                userId = ((Number) rawUserId).intValue();
            } else {
                userId = Integer.parseInt(rawUserId.toString());
            }
        } catch (Exception e) {
            logger.info("Petición inválida a /generate-session: {}", e.getMessage());
            return ServerResponse.badRequest().body(new Response("400", "El user_id proporcionado no es válido"));
        }

        try {
            SessionService.SessionCreationResult res = sessionService.generateSession(userId);

            Map<String, Object> body = Map.of(
                    "code", 201,
                    "msg", "Sesión creada exitosamente",
                    "session_id", res.getSessionId().toString(),
                    "access_token", res.getAccessToken().toString(),
                    "refresh_token", res.getRefreshToken().toString()
            );
            return ServerResponse.status(201).body(body);

        } catch (IllegalArgumentException e) {
            logger.info("Petición inválida a /generate-session: {}", e.getMessage());
            return ServerResponse.badRequest().body(new Response("400", "El user_id proporcionado no es válido o no existe"));
        } catch (Exception e) {
            logger.error("Error creando sesión en /generate-session", e);
            return ServerResponse.status(500).body(new Response("500", "Error al generar la sesión/refresh token"));
        }
    }

    public ServerResponse getSession(ServerRequest request) {
        String idStr = request.pathVariable("id");
        UUID sessionId;
        try {
            sessionId = UUID.fromString(idStr);
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest().body(new Response("400", "session_id inválido"));
        }

        Optional<Session> maybeSession = sessionService.getSessionById(sessionId);

        return maybeSession
                .map(s -> {
                    Map<String, Object> body;
                    if (s.getRefreshToken() != null) {
                        body = Map.of(
                                "id", s.getId().toString(),
                                "access_token", s.getAccessToken().toString(),
                                "refresh_token", s.getRefreshToken().toString(),
                                "user_id", s.getUserId(),
                                "created_at", s.getCreatedAt() == null ? null : s.getCreatedAt().toString(),
                                "expires_at", s.getExpiresAt() == null ? null : s.getExpiresAt().toString(),
                                "refresh_token_expires_at", s.getRefreshTokenExpiresAt() == null ? null : s.getRefreshTokenExpiresAt().toString(),
                                "is_valid", s.isValid()
                        );
                    } else {
                        body = Map.of(
                                "id", s.getId().toString(),
                                "access_token", s.getAccessToken().toString(),
                                "user_id", s.getUserId(),
                                "created_at", s.getCreatedAt() == null ? null : s.getCreatedAt().toString(),
                                "expires_at", s.getExpiresAt() == null ? null : s.getExpiresAt().toString(),
                                "is_valid", s.isValid()
                        );
                    }
                    return ServerResponse.ok().body(body);
                })
                .orElseGet(() -> ServerResponse.status(404).body(new Response("404", "Sesión no encontrada")));
    }

    public ServerResponse refreshToken(ServerRequest request) {
        Map<String, Object> json;
        try {
            json = request.body(Map.class);
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        RefreshTokenRequest dto = new RefreshTokenRequest();
        try {
            Object raw = json.get("refresh_token");
            if (raw == null) {
                throw new IllegalArgumentException("Campo 'refresh_token' requerido");
            }
            dto.setRefresh_token(raw.toString());
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        try {
            String newAccess = sessionService.refreshAccessToken(dto.getRefresh_token());
            return ServerResponse.ok().body(Map.of(
                    "code", 200,
                    "msg", "Token renovado exitosamente",
                    "access_token", newAccess
            ));
        } catch (InvalidSessionException ex) {
            return ServerResponse.status(403).body(new Response("403", "La sesión asociada no es válida"));
        } catch (InvalidRefreshTokenException ex) {
            return ServerResponse.status(401).body(new Response("401", "Refresh token inválido o expirado"));
        } catch (IllegalArgumentException ex) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error en /refresh-token", ex);
            return ServerResponse.status(500).body(new Response("500", "Error interno al refrescar el token"));
        }
    }

    public ServerResponse logout(ServerRequest request) {
        LogoutRequest logoutRequest;
        try {
            logoutRequest = request.body(LogoutRequest.class);
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        try {
            boolean success = sessionService.logout(
                logoutRequest.getEmail(),
                logoutRequest.getPassword(),
                logoutRequest.getSession()
            );

            if (success) {
                return ServerResponse.ok().body(Map.of(
                    "code", 200,
                    "msg", "Ok"
                ));
            } else {
                return ServerResponse.status(401).body(Map.of(
                    "code", 401,
                    "msg", "Unauthorized"
                ));
            }
        } catch (Exception ex) {
            logger.error("Error en /logout", ex);
            return ServerResponse.status(500).body(new Response("500", "Error interno al cerrar sesión"));
        }
    }
}
