package com.SAP.framework.utils.framework;

import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.utils.generic.DateAndTime;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Author: Bharath Kumar Reddy V
 */

public class Screenshot {

    ConfigTestData configTestData=null;
    public Screenshot(ConfigTestData configTestData){
        this.configTestData=configTestData;
    }

    public String getScreenshot(String path) throws FileNotFoundException {
        String screenshotPath = null;

        if(path == null) {
            String screenshotFileName = configTestData.testMethod_Name + "_[" + DateAndTime.getTime() + "]_" + DateAndTime.getDate();
            screenshotPath = "screenshots/" + screenshotFileName + ".png";
            path = ConfigTestData.WORKING_DIR+"/src/test/resources/testReports/" + screenshotPath;

        }

        File file = new File(path);
        path = screenshotPath;

        byte[] screenshot = new byte[0];

        AShot shot = new AShot();
        shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(100));
        ru.yandex.qatools.ashot.Screenshot screen = shot.takeScreenshot(configTestData.driver);
        BufferedImage originalImage = screen.getImage();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            screenshot = baos.toByteArray();
            baos.close();
            InputStream in = new ByteArrayInputStream(screenshot);
            BufferedImage image = ImageIO.read(in);

            ImageIO.write(image, "png", file);
        } catch (Exception noScreenshot) {
        }
        return path;
    }
}
