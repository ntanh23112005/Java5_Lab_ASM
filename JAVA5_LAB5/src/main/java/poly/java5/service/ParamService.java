package poly.java5.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    private final HttpServletRequest request;

    public ParamService(HttpServletRequest request) {
        this.request = request;
    }

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public Date getDate(String name, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(request.getParameter(name));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format");
        }
    }

    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            File saveFile = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new RuntimeException("File saving error");
        }
    }
}
