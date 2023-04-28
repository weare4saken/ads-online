package ru.skypro.project.marketplace.service;

import org.springframework.security.core.Authentication;
import ru.skypro.project.marketplace.dto.AdsCommentDto;
import ru.skypro.project.marketplace.model.Comment;

import java.util.List;

public interface CommentService {

    AdsCommentDto addAdsComment(Integer id, AdsCommentDto adsCommentDto,
                                Authentication authentication);

    void deleteAdsComment(Integer adId, Integer commentId);

    AdsCommentDto updateComments(Integer adId, Integer commentId, AdsCommentDto adsCommentDto);

    List<AdsCommentDto> getComments(Integer id);

    Comment getCommentById(Integer id);

}
