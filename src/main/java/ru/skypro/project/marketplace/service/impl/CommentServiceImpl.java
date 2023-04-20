package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.project.marketplace.dto.AdsCommentDto;
import ru.skypro.project.marketplace.exception.CommentNotFoundException;
import ru.skypro.project.marketplace.mapper.AdsCommentMapper;
import ru.skypro.project.marketplace.model.Comment;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.CommentRepository;
import ru.skypro.project.marketplace.service.CommentService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final AdsServiceImpl adsService;

    @Override
    public List<AdsCommentDto> getComments(Integer id) {
        log.debug("Getting comments for ads with id: {}", id);
        return commentRepository.findAllByAdsId(id)
                .stream()
                .map(AdsCommentMapper.INSTANSE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsCommentDto addAdsComment(Integer id, AdsCommentDto adsCommentDto, Authentication authentication) {
        log.debug("Adding comment for ads with id: {}", id);
        Comment comment = AdsCommentMapper.INSTANSE.toEntity(adsCommentDto);
        User user = userService.getUserByUsername(authentication.getName());
        comment.setAuthor(user);
        comment.setAds(adsService.findAdsById(id));
        comment.setCreatedAt(Instant.now());
        commentRepository.save(comment);
        return AdsCommentMapper.INSTANSE.toDto(comment);
    }

    @Override
    public void deleteAdsComment(Integer adId, Integer commentId) {
        log.debug("Deleting comment with id: {} for ads with id: {}", commentId, adId);
        Comment comment = getAdsComment(commentId, adId);
        commentRepository.delete(comment);
        log.info("Comment removed successfully");
    }

    @Override
    public AdsCommentDto updateComments(Integer adId, Integer commentId,
                                        AdsCommentDto adsCommentDto) {
        log.debug("Updating comment with id: {} for ads with id: {}", commentId, adId);
        Comment adsComment = getAdsComment(commentId, adId);
        adsComment.setText(adsCommentDto.getText());
        commentRepository.save(adsComment);
        return AdsCommentMapper.INSTANSE.toDto(adsComment);
    }

    public Comment getAdsComment(Integer commentId, Integer adId) {
        log.debug("Getting comment with id: {} for ads with id: {}", commentId, adId);
        return commentRepository.findByIdAndAdsId(commentId, adId).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public Comment getCommentById(Integer id) {
        log.debug("Getting comment with id: {}", id);
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

}
