package com.neuralnetwork.ocr;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] trainingInput = new float[30][64];
		for (int i = 0; i < 10; i ++) {

			trainingInput[i] = ImageReader
					.readImage("D:\\Java_Courses\\JavaFX\\OpticalCharacterRecognition\\assets\\" + i + ".png");
			trainingInput[i] = ImageReader
					.readImage("D:\\Java_Courses\\JavaFX\\OpticalCharacterRecognition\\assets\\" + i + "" + i + ".png");
			trainingInput[i] = ImageReader.readImage(
					"D:\\Java_Courses\\JavaFX\\OpticalCharacterRecognition\\assets\\" + i + "" + i + "" + i + ".png");
		}
		float[][] trainingOutput = new float[30][10];
		for (int i = 0; i < trainingOutput.length; i += 3) {
			trainingOutput[i] = new float[10];
			trainingOutput[i][i / 3] = 1f;
			trainingOutput[i + 1][i / 3] = 1f;
			trainingOutput[i + 2][i / 3] = 1f;
			// Setting true for example when i = 0
			// row 1: 1 0 0 0 0 0 0 0 0 0 0
			// row2: 1 0 0 0 0 0 0 0 0 0 0
			// row3: 1 0 0 0 0 0 0 0 0 0 0
		}

		System.out.println("Training the network...");
		NeuralNetwork nn = new NeuralNetwork(64, 15, 10);
		for (int i = 0; i < Constants.ITERATIONS; i++) {
			for (int j = 0; j < trainingInput.length; j++) {
				nn.train(trainingInput[j], trainingOutput[j], Constants.learningRate, Constants.momentum);
			}
		}
		System.out.println("Training done! \n");
		for (int i = 0; i < 5; i++) {
			float[] test = ImageReader
					.readImage("D:\\Java_Courses\\JavaFX\\OpticalCharacterRecognition\\assets\\test" + (char)('a'+i) + ".png");
			float[] calcOutput = nn.run(test);
			for (int k = 0; k < calcOutput.length; k++) {
				System.out.print(Math.round(calcOutput[k]) + ", ");
			}
			System.out.println();
		}
	}


}
