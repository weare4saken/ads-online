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
        log.debug("Removing avatar with id {}", image.getId());
        imageRepository.delete(image);
        log.info("Avatar removed successfully");
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) throws IOException {
        log.debug("Uploading inage file: " + imageFile.getOriginalFilename());
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setFileSize(imageFile.getSize());
        image.setData(imageFile.getBytes());
        Image savedImage = imageRepository.save(image);
        log.info("Image successfully uploaded with id {}", savedImage.getId());
        return savedImage;
    }

    @Override
    public Image getImageById(Integer id) {
        log.debug("Getting image with id: {}", id);
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public Pair<String, byte[]> getImage(Integer id) {
        Image image = getImageById(id);
        return Pair.of(image.getMediaType(), image.getData());
    }

}
