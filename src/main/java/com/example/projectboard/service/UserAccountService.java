package com.example.projectboard.service;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.UserAccountReq;
import com.example.projectboard.model.entity.UserAccount;
import com.example.projectboard.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount createUserAccount(Header<UserAccountReq> dto) {
        var userDto = dto.getData();
        var id = userDto.getUserId();
        var password = userDto.getUserPassword();
        var email = userDto.getEmail();
        var nickname = userDto.getNickname();
        var memo = userDto.getMemo();

        // TODO - convert entity to dto using mapper
        UserAccount userAccount = UserAccount.of(id, password, email, nickname, memo);
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findByUserId(String userId) {
        // TODO - convert entity to dto using mapper
        return userAccountRepository.findByUserId(userId).orElseThrow();
    }
}
