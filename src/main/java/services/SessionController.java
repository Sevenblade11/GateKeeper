package services;

import hash.HashUtils;
import mvc.model.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.RestController;
import repository.SessionRepository;

import java.util.List;

@RestController
public class SessionController {

    private static final Logger logger = LogManager.getLogger();
    private static SessionRepository sessionRepository;

    public SessionController (SessionRepository sessionRepository){
        SessionController.sessionRepository = sessionRepository;
    }

    public static boolean isValidSession(String sessionToken, long id){
        List<Session> sessions = sessionRepository.findAll();
        for(Session s: sessions)
            if(s.getSessionToken().equals(sessionToken) && s.getUserId() == id)
                return true;
        return false;
    }

    public static void createSession(Session session){
        List<Session> sessions = sessionRepository.findAll();
        for(Session s: sessions)
            if(s.getUserId() == session.getUserId())
               sessionRepository.delete(s);
        sessionRepository.save(session);
    }


}
