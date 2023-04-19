package ru.skypro.project.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.project.marketplace.dto.AdsDto;
import ru.skypro.project.marketplace.model.Ads;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    List<Ads> findAllByAuthorId(Integer id);

//    List<Ads> findByUsers(User user);

//    List<Ads> findByTitleContains(String partOfTitle);

//    List<Ads> findByDescriptionContains(String partOfDescription);

//    List<Ads> findByPrice(Integer price);

//    List<Ads> findByPriceLessThanEqual(Integer maxPrice);

//    List<Ads> findByPriceGreaterThanEqual(Integer minPrice);

//    List<Ads> findByPriceBetween(Integer minPrice, Integer maxPrice);

//    List<Ads> findByTitleContainsAndDescriptionContains(String partOfTitle, String partOfDescription); //а надо ли?

//    List<Ads> findByTitleContainsAndDescriptionContainsAndPriceLessThanEqualOrderByPrice(String partOfTitle,
//                                                                                         String partOfDescription,
//                                                                                         Integer maxPrice);

//    List<Ads> findByTitleContainsAndDescriptionContainsAndPriceGreaterThanEqualOrderByPrice(String partOfTitle,
//                                                                                            String partOfDescription,
//                                                                                            Integer minPrice);

//    List<Ads> findByTitleContainsAndDescriptionContainsAndPriceBetween(String partOfTitle,
//                                                                       String partOfDescription,
//                                                                       Integer minPrice,
//                                                                       Integer maxPrice);

}

