package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.project.marketplace.exception.ImageNotFoundException;
import ru.skypro.project.marketplace.model.Image;
import ru.skypro.project.marketplace.repository.ImageRepository;
import ru.skypro.project.marketplace.service.ImageService;

import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService<Image> {

    private final ImageRepository imageRepository;

    @Override
    public void remove(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setFileSize(imageFile.getSize());
        image.setData(imageFile.getBytes());
        return imageRepository.save(image);
    }

    public Image getImageById(Integer id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public Pair<String, byte[]> getImage(Integer id) {
        Image image = getImageById(id);
        return Pair.of(image.getMediaType(), image.getData());
    }

}
