package kz.aldi.chat.services.impl;

import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.models.dto.UserDto;
import kz.aldi.chat.repositories.UserRepository;
import kz.aldi.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(UserDto userDto) {
        log.info("service userDto request: {}", userDto);
        User user = User.builder()
                .name(userDto.getName())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);
        log.info("service user response: {}", user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(UserDto userDto, Long id) {
        return null;
    }
}
