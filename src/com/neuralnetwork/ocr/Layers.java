package com.neuralnetwork.ocr;

import java.util.Arrays;
import java.util.Random;

public class Layers {

	float[] input;
	float[] output;
	float[] weights;
	float[] dweights;
	Random rand;

	public Layers(int inputSize, int outputSize) {
		this.input = new float[inputSize + 1];
		this.output = new float[outputSize];
		this.weights = new float[(inputSize + 1) * outputSize];
		this.dweights = new float[weights.length];
		rand = new Random();
		initializeWeights();

	}

	private void initializeWeights() {
		for (int i = 0; i < weights.length; i++)
			weights[i] = (rand.nextFloat() - 0.5f) * 4f;
	}

	public float[] run(float[] inputActivation) {
		System.arraycopy(inputActivation, 0, this.input, 0, inputActivation.length);
		int offset = 0;
		this.input[this.input.length - 1] = 1;
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < input.length; j++)
				output[i] += weights[offset + j] * input[j];
			output[i] = ActivationFunction.signmoid(output[i]);
			offset += input.length;
		}

		return Arrays.copyOf(output, output.length);
	}

	public float[] train(float[] error, float learningRate, float momentum) {
		float[] nextError = new float[input.length];
		int offset = 0;
		for (int i = 0; i < output.length; i++) {
			float delta = ActivationFunction.dsignmoid(output[i]) * error[i];
			for (int j = 0; j < input.length; j++) {
				float dw = delta * learningRate * input[j];
				int widx = offset + j;
				nextError[j] = nextError[j] + weights[widx] * delta;
				weights[widx] += dw + dweights[widx] * momentum;
				dweights[widx] = dw;
			}
			offset += input.length;
		}
		return nextError;

	}

}
