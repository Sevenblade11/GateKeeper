package services;

import hash.HashUtils;
import mvc.model.Session;
import mvc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.SessionRepository;
import repository.UserRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private static final Logger logger = LogManager.getLogger();
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Session> logIn(@RequestBody Map<String, String> body){
        List<User> userList = userRepository.findAll();
        Session session = new Session();
        logger.info("Executing Log In.");
        for(User user: userList){
            if(user.getUsername().equals(body.get("username")) && user.getPassword().equals(body.get("password"))){
                logger.info("Successfully found user id.");
                session.setUserId(user.getId());
                session.setSessionToken(HashUtils.getCryptoHash(LocalTime.now().toString(), "SHA-256"));
                SessionController.createSession(session);
                return new ResponseEntity<>(session, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(session, HttpStatus.UNAUTHORIZED);
    }

}
