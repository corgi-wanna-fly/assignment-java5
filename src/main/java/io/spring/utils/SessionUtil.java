package io.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionUtil {
    @Autowired
    HttpSession session;

    public void setAttribute(String name, Object value){
        session.setAttribute(name, value);
    }

    public <T> T getAttribute(String name){
        return (T) session.getAttribute(name);
    }

    public void removeAttribute(String name){
        session.removeAttribute(name);
    }
}
