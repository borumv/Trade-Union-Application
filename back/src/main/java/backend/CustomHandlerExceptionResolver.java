package backend;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

@Component("customExceptionResolver")
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof ExpiredJwtException) {
            // Отправляем пользователю сообщение об ошибке с информацией о просроченном токене
            sendErrorResponse(response, "Expired JWT token");
        }
        // Возвращаем null, чтобы показать, что исключение не было полностью обработано и позволить другим резолверам обработать его.
        return null;
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        try (PrintWriter out = response.getWriter()) {
            out.print("{\"error\": \"" + errorMessage + "\"}");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
