package ru.skypro.project.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.project.marketplace.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
