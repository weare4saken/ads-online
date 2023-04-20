package ru.skypro.project.marketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.project.marketplace.dto.UserDto;
import ru.skypro.project.marketplace.model.Avatar;
import ru.skypro.project.marketplace.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    String USER_AVATAR = "/users/avatar/";
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "image", source = "avatar", qualifiedByName = "avatarMapping")
    UserDto toDto(User user);

    @Named("avatarMapping")
    default String avatarMapping(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return USER_AVATAR + avatar.getId();
    }

}
