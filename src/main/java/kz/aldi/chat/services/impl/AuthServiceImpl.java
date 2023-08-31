package kz.aldi.chat.services.impl;

import kz.aldi.chat.models.dto.RegisterDto;
import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.repositories.UserRepository;
import kz.aldi.chat.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @Override
    public String register(RegisterDto registerDto) {
        var userOpt = userRepository.findUserByLogin(registerDto.getLogin());
        if (userOpt.isEmpty()) {
            return "user not found";
        }
        User user = userOpt.get();
        if(user.getPassword().equals(registerDto.getPassword())){
            return "welcome";
        }
        return "incorrect password";
    }
}
