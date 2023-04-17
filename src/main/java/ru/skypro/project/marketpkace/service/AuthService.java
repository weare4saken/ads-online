package ru.skypro.project.marketpkace.service;

import ru.skypro.project.marketpkace.dto.RegisterReq;
import ru.skypro.project.marketpkace.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
}
