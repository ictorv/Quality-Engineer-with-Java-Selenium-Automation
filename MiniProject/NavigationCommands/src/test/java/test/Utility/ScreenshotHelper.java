package test.Utility;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class ScreenshotHelper {
 
    public static String filepath = System.getProperty("user.dir") + File.separator + "screenshots";
 
	/*
	 * public static String screenShotTC(WebDriver scdriver, String fileName) throws
	 * IOException { // Ensure the screenshots folder exists File folder = new
	 * File(filepath); if (!folder.exists()) { folder.mkdirs(); }
	 * 
	 * // Format the date and generate the destination path DateFormat dateFormat =
	 * new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss"); Date date = new Date(); String
	 * destination = filepath + File.separator + fileName + "_" +
	 * dateFormat.format(date) + ".png";
	 * 
	 * // Capture the screenshot File src = ((TakesScreenshot)
	 * scdriver).getScreenshotAs(OutputType.FILE); File dest = new
	 * File(destination);
	 * 
	 * try { FileHandler.copy(src, dest); } catch (IOException e) {
	 * System.out.println("Screenshot capture failed: " + e.getMessage()); }
	 * 
	 * return destination; }
	 */
    
    
	/*
	 * public static String screenShotTC(WebDriver scdriver, String fileName) throws
	 * IOException { File folder = new File(filepath); if (!folder.exists()) {
	 * folder.mkdirs(); }
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss"); Date
	 * date = new Date(); String screenshotName = fileName + "_" +
	 * dateFormat.format(date) + ".png"; String destination = filepath +
	 * File.separator + screenshotName;
	 * 
	 * File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
	 * File dest = new File(destination);
	 * 
	 * try { FileHandler.copy(src, dest); } catch (IOException e) {
	 * System.out.println("Screenshot capture failed: " + e.getMessage()); }
	 * 
	 * return destination; // return full path }
	 */
    public static String screenShotTC(WebDriver scdriver, String fileName) throws IOException {
        File folder = new File(filepath);
        if (!folder.exists()) folder.mkdirs();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String screenshotName = fileName + "_" + dateFormat.format(new Date()) + ".jpg"; // save as JPEG
        String destination = filepath + File.separator + screenshotName;

        // Take screenshot as file
        File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(src);

        // Set up compressed JPEG writer
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        FileImageOutputStream output = new FileImageOutputStream(new File(destination));
        writer.setOutput(output);

        ImageWriteParam jpgParams = writer.getDefaultWriteParam();
        jpgParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgParams.setCompressionQuality(0.5f); // 50% quality

        writer.write(null, new IIOImage(image, null, null), jpgParams);
        writer.dispose();
        output.close();

        return destination;
    }
}