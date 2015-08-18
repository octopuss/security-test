package sk.octopuss.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by ivan.dolezal.ext on 18.8.2015.
 */
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheTest {

    @Autowired
    ImageService imageService;

    Log logger = LogFactory.getLog(CacheTest.class);

    @Test
    public void testImageCache() throws Exception {
        logger.info(new Date());
        byte[] data = imageService.getImage();
        data.toString();
        Thread.sleep(1000);
        data = imageService.getImage();
        logger.info(new Date());
        Thread.sleep(1000);
        data = imageService.getImage();
        logger.info(new Date());
    }
}
