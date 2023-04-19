package ru.skypro.project.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.project.marketplace.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}
