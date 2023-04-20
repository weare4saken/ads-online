package ru.skypro.project.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.project.marketplace.dto.NewPassword;
import ru.skypro.project.marketplace.dto.UserDto;
import ru.skypro.project.marketplace.service.impl.AvatarServiceImpl;
import ru.skypro.project.marketplace.service.impl.UserServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserServiceImpl userService;
    private final AvatarServiceImpl avatarService;

    @Operation(
            summary = "Обновление пароля", tags = {"Пользователи"},
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                        schema = @Schema(implementation = NewPassword.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content), //где получить?
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                         Authentication authentication) {
        userService.updatePassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить информацию об авторизованном пользователе", tags = {"Пользователи"},
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                        schema = @Schema(implementation = UserDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content), //где получить?
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUser(authentication));
    }

    @Operation(
            summary = "Обновить информацию об авторизованном пользователе", tags = {"Пользователи"},
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                        schema = @Schema(implementation = UserDto.class))}),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content), //где получить?
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(userDto, authentication));
    }

    @Operation(
            summary = "Обновить аватар авторизованного пользователя", tags = {"Пользователи"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping(value = "/me/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateUserAvatar(@RequestPart MultipartFile avatarFile,
                                              Authentication authentication) throws IOException {
        userService.updateUserAvatar(avatarFile, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/avatar/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getAvatar(@PathVariable Integer id) {
        Pair<String, byte[]> pair = avatarService.getImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pair.getLeft()))
                .contentLength(pair.getRight().length)
                .body(pair.getRight());
    }

   /*@GetMapping(value = "/avatar/{id}")
//    , produces = {MediaType.IMAGE_JPEG_VALUE})
//
   public byte[] getAvatar(@PathVariable("id") Integer id) throws IOException {
       return Files.readAllBytes(Paths.get("file:///C:/Users/kolod/Desktop/2c3d523050198c48c9ec8bbde64224d7.jpg"));
//               avatarService.getAvatarById(id).getData();


   }*/
}
