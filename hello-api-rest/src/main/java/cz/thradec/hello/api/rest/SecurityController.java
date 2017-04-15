package cz.thradec.hello.api.rest;

import static org.springframework.security.core.authority.AuthorityUtils.authorityListToSet;

import java.util.Set;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @GetMapping("user")
    public UserData getUserData(Authentication authentication) {
        UserData userData = new UserData();
        if ( authentication != null ) {
            userData.setName(authentication.getName());
            userData.setRoles(authorityListToSet(authentication.getAuthorities()));
        }
        return userData;
    }

    @Data
    private static class UserData {
        String name;
        Set<String> roles;
    }

}
