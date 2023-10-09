package com.example.projectboard.controller;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.UserAccountReq;
import com.example.projectboard.model.entity.UserAccount;
import com.example.projectboard.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/users")
    public Header<?> createUser(@Valid @RequestBody Header<UserAccountReq> dto){
        UserAccount userAccount = userAccountService.createUserAccount(dto);
        return Header.ok(userAccount);
    }

    @GetMapping("/users/{userId}")
    public Header<?> getUser(@PathVariable("userId") String userId){
        UserAccount userAccount = userAccountService.findByUserId(userId);
        return Header.ok(userAccount);
    }

}
