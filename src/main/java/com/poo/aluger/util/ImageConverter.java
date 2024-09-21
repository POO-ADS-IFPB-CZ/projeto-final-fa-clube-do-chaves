package com.poo.aluger.util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static byte[] convertImageToBytes(String imagePath) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    public static void convertBytesToImage(byte[] imageBytes) throws IOException {

        String outputImagePath = "assets/img/";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        if (bufferedImage == null) {
            throw new IOException("Não foi possível converter o array de bytes em imagem.");
        }

        File outputFile = new File(outputImagePath);
        ImageIO.write(bufferedImage, "jpg", outputFile);

    }
}
