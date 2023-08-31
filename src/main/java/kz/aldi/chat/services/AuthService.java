package kz.aldi.chat.services;

import kz.aldi.chat.models.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
