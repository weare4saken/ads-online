package ru.skypro.project.marketplace.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.project.marketplace.model.Image;
import ru.skypro.project.marketplace.repository.ImageRepository;
import ru.skypro.project.marketplace.service.impl.ImageServiceImpl;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void testRemove() {
        Image image = new Image();
        image.setId(1);
        image.setData("image".getBytes());

        doNothing().when(imageRepository).delete(image);

        imageService.remove(image);

        verify(imageRepository, only()).delete(image);
        verifyNoMoreInteractions(imageRepository);
    }

}
