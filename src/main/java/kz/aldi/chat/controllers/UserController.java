package kz.aldi.chat.controllers;

import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.models.dto.UserDto;
import kz.aldi.chat.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        log.info("userDto request: {}", userDto);
        User user = new User();
        user = userService.create(userDto);
        log.info("user response: {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable("userId") Long id){
        var userOpt = userService.findById(id);
        if(userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.ok("User not found");
    }
}
