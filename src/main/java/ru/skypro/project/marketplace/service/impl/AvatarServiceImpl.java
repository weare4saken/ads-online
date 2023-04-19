package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.project.marketplace.exception.ImageNotFoundException;
import ru.skypro.project.marketplace.model.Avatar;
import ru.skypro.project.marketplace.repository.AvatarRepository;
import ru.skypro.project.marketplace.service.ImageService;

import java.io.IOException;

//@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AvatarServiceImpl implements ImageService<Avatar> {

    private final AvatarRepository avatarRepository;

    @Override
    public void remove(Avatar avatar) {
        avatarRepository.delete(avatar);
    }

    @Override
    public Avatar uploadImage(MultipartFile avatarFile) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setData(avatarFile.getBytes());
        return avatarRepository.save(avatar);
    }

    public Avatar getAvatarById(Integer id) {
        return avatarRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public Pair<String, byte[]> getImage(Integer id) {
        Avatar avatar = getAvatarById(id);
        return Pair.of(avatar.getMediaType(), avatar.getData());
    }

}
