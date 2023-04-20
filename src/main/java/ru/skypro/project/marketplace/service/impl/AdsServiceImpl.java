package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.project.marketplace.dto.AdsDto;
import ru.skypro.project.marketplace.dto.CreateAds;
import ru.skypro.project.marketplace.dto.FullAds;
import ru.skypro.project.marketplace.exception.AdsNotFoundException;
import ru.skypro.project.marketplace.mapper.AdsMapper;
import ru.skypro.project.marketplace.model.Ads;
import ru.skypro.project.marketplace.model.Image;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.AdsRepository;
import ru.skypro.project.marketplace.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final UserServiceImpl userService;
    private final AdsRepository adsRepository;
    private final ImageServiceImpl imageService;

    @Override
    public List<AdsDto> getAllAds() {
        log.debug("Getting all ads");
        return adsRepository.findAll()
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsDto addAds(MultipartFile imageFile, CreateAds createAds, Authentication authentication) throws IOException {
        log.debug("Adding ads");
        Ads ads = AdsMapper.INSTANCE.toEntity(createAds);
        User user = userService.getUserByUsername(authentication.getName());
        ads.setAuthor(user);
        Image image = imageService.uploadImage(imageFile);
        ads.setImage(image);
        return AdsMapper.INSTANCE.toDto(adsRepository.save(ads));
    }

    public FullAds getAdsById(Integer id) {
        log.debug("Getting ads by id: {}", id);
        return AdsMapper.INSTANCE.toFullAds(findAdsById(id));
    }

    @Override
    public void removeAdsById(Integer id) {
        log.debug("Removing ads by id: {}", id);
        Ads ads = findAdsById(id);
        adsRepository.delete(ads);
        log.info("Ads removed successfully");
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAds createAds) {
        log.debug("Updating ads by id: {}", id);
        Ads ads = findAdsById(id);
        ads.setTitle(createAds.getTitle());
        ads.setDescription(createAds.getDescription());
        ads.setPrice(createAds.getPrice());
        adsRepository.save(ads);
        log.info("Ads details updated for ads: {}", ads.getTitle());
        return AdsMapper.INSTANCE.toDto(ads);
    }

    @Override
    public List<AdsDto> getAdsMe(Authentication authentication) {
        log.debug("Getting ads by author {}", authentication.getName());
        return adsRepository.
                findAllByAuthorId(userService.getUserByUsername(authentication.getName()).getId())
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException {
        log.debug("Updating ads image by id: {}", id);
        Ads ads = findAdsById(id);
        imageService.remove(ads.getImage());
        ads.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ads);
    }

    public Ads findAdsById(Integer id) {
        log.debug("Finding ads by id: {}", id);
        return adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
    }

}

