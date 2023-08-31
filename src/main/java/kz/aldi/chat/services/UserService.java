package kz.aldi.chat.services;

import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.models.dto.UserDto;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {
    User create(UserDto userDto);
    Optional<User> findById(Long id);
    User update(UserDto userDto, Long id);

}
