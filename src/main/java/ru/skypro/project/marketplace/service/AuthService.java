package ru.skypro.project.marketplace.service;

import ru.skypro.project.marketplace.dto.RegisterReq;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegisterReq registerReq);

}
