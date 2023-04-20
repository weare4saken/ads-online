package ru.skypro.project.marketplace.service;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService<T> {

    void remove(T object);

    T uploadImage(MultipartFile file) throws IOException;

    T getImageById(Integer id);

    Pair<String, byte[]> getImage(Integer id);

}
