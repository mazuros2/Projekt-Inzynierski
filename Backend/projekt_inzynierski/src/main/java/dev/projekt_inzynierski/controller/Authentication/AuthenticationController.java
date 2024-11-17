package dev.projekt_inzynierski.controller.Authentication;


import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationRequest;
import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationResponse;
import dev.projekt_inzynierski.configurationJWT.Authentication.RegisterRequest;
import dev.projekt_inzynierski.service.Authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/registerNewUser")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthenticationResponse> registerNewUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<AuthenticationResponse> registerNewUser(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
