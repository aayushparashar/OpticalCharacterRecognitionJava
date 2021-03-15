package com.neuralnetwork.ocr;

import java.util.Arrays;

public class NeuralNetwork {
	Layers[] layers;

	NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
		layers = new Layers[2];
		layers[0] = new Layers(inputSize, hiddenSize);
		layers[1] = new Layers(hiddenSize, outputSize);
	}

	public Layers getLayer(int idx) {
		return layers[idx];
	}

	public float[] run(float[] input) {
		float[] inputActivation = input;
		for (int i = 0; i < layers.length; i++)
			inputActivation = layers[i].run(inputActivation);
		return inputActivation;
	}

	public void train(float[] input, float[] output, float learningRate, float momentum) {
		float[] calculatedOutput = run(input);
		float[] error = new float[calculatedOutput.length];
		for (int i = 0; i < error.length; i++)
			error[i] = output[i] - calculatedOutput[i];
		for (int i = layers.length - 1; i >= 0; i--)
			error = layers[i].train(error, learningRate, momentum);

	}

}
