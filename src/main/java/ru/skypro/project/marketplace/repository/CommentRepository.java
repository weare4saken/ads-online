package ru.skypro.project.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.project.marketplace.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findByIdAndAdsId(Integer commentId, Integer adId);

    List<Comment> findAllByAdsId (Integer id);

}
