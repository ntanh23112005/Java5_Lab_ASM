package poly.java5.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
