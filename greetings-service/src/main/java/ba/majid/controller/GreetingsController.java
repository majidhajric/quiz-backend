package ba.majid.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/resources")
public class GreetingsController {

    private final Integer port;

    public GreetingsController(@Value("${server.port}") Integer port) {
        this.port = port;
    }

    @GetMapping(path = "/greetings", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greeting() {
        log.debug("Unsecured request");
        return "Hello from port: " + port;
    }

    @GetMapping(path = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetingSecured(@AuthenticationPrincipal Jwt jwt) {
        log.debug("Secured request, user: {}", jwt.getSubject());
        return "Echo from SECURED endpoint, in=: " + jwt.getClaimAsString("email");
    }
}
