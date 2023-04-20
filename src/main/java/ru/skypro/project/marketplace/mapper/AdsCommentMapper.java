package ru.skypro.project.marketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.project.marketplace.dto.AdsCommentDto;
import ru.skypro.project.marketplace.model.Comment;
import ru.skypro.project.marketplace.model.Avatar;

@Mapper(componentModel = "spring")
public interface AdsCommentMapper {

    String USER_AVATAR = "/users/avatar/";
    AdsCommentMapper INSTANSE = Mappers.getMapper(AdsCommentMapper.class);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ads", ignore = true)
    Comment toEntity(AdsCommentDto dto);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", source = "author.avatar", qualifiedByName = "avatarMapping")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    AdsCommentDto toDto(Comment entity);

    @Named("avatarMapping")
    default String avatarMapping(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return USER_AVATAR + avatar.getId();
    }

}
