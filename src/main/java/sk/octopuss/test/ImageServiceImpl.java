package sk.octopuss.test;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by ivan.dolezal.ext on 18.8.2015.
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Cacheable(value="Image")
    public byte[] getImage() {
        try {
            BufferedImage image = ImageIO.read(new File("c:\\Users\\ivan.dolezal.ext\\Downloads\\world.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
