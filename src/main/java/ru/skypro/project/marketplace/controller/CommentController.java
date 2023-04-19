package ru.skypro.project.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.project.marketplace.dto.AdsCommentDto;
import ru.skypro.project.marketplace.dto.ResponseWrapper;
import ru.skypro.project.marketplace.service.CommentService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Получить комментарии объявления", description = "getComments", tags = {"Комментарии"})
    @GetMapping("/{id}/comments")
    public ResponseWrapper<AdsCommentDto> getComments(@PathVariable("id") Integer id) {
        return ResponseWrapper.of(commentService.getComments(id));
    }

    @Operation(
            summary = "Добавление нового комментария к объявлению", tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                        schema = @Schema(implementation = AdsCommentDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComment(@PathVariable("id") Integer id,
                                                       @RequestBody AdsCommentDto adsCommentDto,
                                                       Authentication authentication) {
        return ResponseEntity.ok(commentService.addAdsComment(id, adsCommentDto, authentication));
    }

    @Operation(
            summary = "Удалить комментарий", tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PreAuthorize("commentServiceImpl.getCommentById(#commentId).getAuthor().username" +
            "== authentication.principal.username or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteAdsComment(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Integer commentId) {
        commentService.deleteAdsComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновить комментарий", tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                        schema = @Schema(implementation = AdsCommentDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PreAuthorize("commentServiceImpl.getCommentById(#commentId).getAuthor().username" +
            "== authentication.principal.username or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<AdsCommentDto> updateComments(@PathVariable("adId") Integer adId,
                                                        @PathVariable("commentId") Integer commentId,
                                                        @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(commentService.updateComments(adId, commentId, adsCommentDto));
    }

}
