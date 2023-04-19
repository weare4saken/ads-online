package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
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

//@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final UserServiceImpl userService;
    private final AdsRepository adsRepository;
    private final ImageServiceImpl imageService;

    @Override
    public List<AdsDto> getAllAds() {
        return adsRepository.findAll()
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsDto addAds(MultipartFile imageFile, CreateAds createAds, Authentication authentication) throws IOException {
        Ads ads = AdsMapper.INSTANCE.toEntity(createAds);
        User user = userService.getUserByUsername(authentication.getName());
        ads.setAuthor(user);
        Image image = imageService.uploadImage(imageFile);
        ads.setImage(image);
        return AdsMapper.INSTANCE.toDto(adsRepository.save(ads));
    }

    public FullAds getAdsById(Integer id) {
        return AdsMapper.INSTANCE.toFullAds(findAdsById(id));
    }

    @Override
    public void removeAdsById(Integer id) {
        Ads ads = findAdsById(id);
        adsRepository.delete(ads);
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAds createAds) {
        Ads ads = findAdsById(id);
        ads.setTitle(createAds.getTitle());
        ads.setDescription(createAds.getDescription());
        ads.setPrice(createAds.getPrice());
        adsRepository.save(ads);
        return AdsMapper.INSTANCE.toDto(ads);
    }

    @Override
    public List<AdsDto> getAdsMe(Authentication authentication) {
        return adsRepository.
                findAllByAuthorId(userService.getUserByUsername(authentication.getName()).getId())
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException {
        Ads ads = findAdsById(id);
        imageService.remove(ads.getImage());
        ads.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ads);
    }

    public Ads findAdsById(Integer id) {
        return adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
    }

}

