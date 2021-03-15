package com.neuralnetwork.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	private ImageReader() {
	}

	public static float[] readImage(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			float[] ans = new float[image.getWidth()*image.getHeight()];
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					ans[i*image.getHeight()+j] = (image.getRGB(i, j) == 0xFFFFFFFF? 0f : 1f);
				}
			}
			return ans;
		} catch (IOException e) {
			return new float[64];
		}
	}

}
