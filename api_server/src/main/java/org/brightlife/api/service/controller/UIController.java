package org.brightlife.api.service.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UIController {

    @PostMapping("/facebooklogin")
    public Principal getUser(Principal user) {
        return user;
    }
}
