package com.neuralnetwork.ocr;

public class ActivationFunction {
	private ActivationFunction() {
	};

	public static float signmoid(float x) {
		return (float) (1 / (1 + Math.exp(-x)));
	}

	public static float dsignmoid(float x) {
		return x * (1 - x);
	}

}
