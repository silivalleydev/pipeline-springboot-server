package com.api.pipeline.erp.controller;

import com.api.pipeline.erp.dto.SignInDto;
import com.api.pipeline.erp.dto.TokenDto;
import com.api.pipeline.erp.dto.UserDto;
import com.api.pipeline.erp.dto.UserResponseDto;
import com.api.pipeline.erp.entity.User;
import com.api.pipeline.erp.service.UserService;
import com.api.pipeline.main.jwt.JwtFilter;
import com.api.pipeline.main.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "erp.authController")
@RequestMapping("/api/user")
public class UserController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public UserController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/sign/in")
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody SignInDto signInDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInDto.getUserId(), signInDto.getPassword());

        System.out.println("id=>" + signInDto.getUserId() + ", password=>" + signInDto.getPassword());
        System.out.println("auth=>" + authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication, 1);
        String refreshToken = tokenProvider.createToken(authentication, 30);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/sign/up")
    public ResponseEntity<Map> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        User user = userService.signup(userDto);

        System.out.println("결과?? " + user);

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "success");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/myinfo")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {

        User rsUser = userService.getMyUserWithAuthorities().get();
        System.out.println(rsUser.getUserId());
        UserResponseDto responseUser = new UserResponseDto();

        responseUser.setName(rsUser.getName());
        responseUser.setCompany(rsUser.getCompany());
        responseUser.setDepartment(rsUser.getDepartment());
        responseUser.setEmail(rsUser.getEmail());
        responseUser.setPhone(rsUser.getPhone());

        return ResponseEntity.ok(responseUser);
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}
