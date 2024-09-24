package com.poo.aluger.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class ImageConverter {
  public static byte[] convertImageToBytes(String imagePath) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static BufferedImage convertBytesToImage(byte[] imageBytes) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
    return ImageIO.read(byteArrayInputStream);
  }

  public static byte[] convertBufferedImageToBytes(BufferedImage bufferedImage) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static Image getImage(BufferedImage img) {
    BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
    newImg.createGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);

    // converting the BufferedImage to an IntBuffer
    int[] type_int_agrb = ((DataBufferInt) newImg.getRaster().getDataBuffer()).getData();
    IntBuffer buffer = IntBuffer.wrap(type_int_agrb);

    // converting the IntBuffer to an Image, read more about it here:
    // https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
    PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
    PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer(newImg.getWidth(), newImg.getHeight(), buffer, pixelFormat);
    return new WritableImage(pixelBuffer);
  }
}
