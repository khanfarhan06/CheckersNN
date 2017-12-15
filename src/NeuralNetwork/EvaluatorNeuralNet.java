package NeuralNetwork;

import Checkers.CheckersBoard;
import Checkers.Piece;

import java.io.Serializable;
import java.util.Random;

public class EvaluatorNeuralNet implements Serializable{

    private static final int inputNodes = 32;
    private final int nodeCountHiddenLayer1, nodeCountHiddenLayer2;
    float kingValue;
    float[][] weightInputToHidden1, weightHidden1ToHidden2;
    float[] weightHidden2ToOutput;

    public EvaluatorNeuralNet(int nodeCountHiddenLayer1, int nodeCountHiddenLayer2) {
        this.nodeCountHiddenLayer1 = nodeCountHiddenLayer1;
        this.nodeCountHiddenLayer2 = nodeCountHiddenLayer2;

        weightInputToHidden1= new float[inputNodes][nodeCountHiddenLayer1];
        weightHidden1ToHidden2 = new float[nodeCountHiddenLayer1][nodeCountHiddenLayer2];
        weightHidden2ToOutput = new float[nodeCountHiddenLayer2];

        initializeWithRandom(weightInputToHidden1);
        initializeWithRandom(weightHidden1ToHidden2);
        initializeWithRandom(weightHidden2ToOutput);

        kingValue = 2;
    }

    public EvaluatorNeuralNet(EvaluatorNeuralNet evaluatorNeuralNet){
        this.nodeCountHiddenLayer1 = evaluatorNeuralNet.nodeCountHiddenLayer1;
        this.nodeCountHiddenLayer2 = evaluatorNeuralNet.nodeCountHiddenLayer2;

        weightInputToHidden1= new float[inputNodes][nodeCountHiddenLayer1];
        weightHidden1ToHidden2 = new float[nodeCountHiddenLayer1][nodeCountHiddenLayer2];
        weightHidden2ToOutput = new float[nodeCountHiddenLayer2];

        initializeWithGaussianRandomChange(this.weightInputToHidden1, evaluatorNeuralNet.weightInputToHidden1);
        initializeWithGaussianRandomChange(this.weightHidden1ToHidden2, evaluatorNeuralNet.weightHidden1ToHidden2);
        initializeWithGaussianRandomChange(this.weightHidden2ToOutput, evaluatorNeuralNet.weightHidden2ToOutput);

        this.kingValue = (float) (evaluatorNeuralNet.kingValue + Math.random()-0.5);
    }


    public double evaluate(CheckersBoard checkersBoard){
        float[] input = getInputArray(checkersBoard);

        float[] outputOfHidden1 = activationFunction(multiply(input, weightInputToHidden1));

        float[] outputOfHidden2 = activationFunction(multiply(outputOfHidden1, weightHidden1ToHidden2));

        float evaluatedValue = activationFunction(multiply(outputOfHidden2, weightHidden2ToOutput));
        return evaluatedValue;
    }

    private void initializeWithGaussianRandomChange(float[][] newWeights, float[][] originalWeights){
        for (int i = 0; i < originalWeights.length; i++) {
            Random random = new Random();
            for (int j = 0; j < originalWeights[0].length; j++) {
                newWeights[i][j] = (float) (originalWeights[i][j] + random.nextGaussian());
            }
        }
    }

    private void initializeWithGaussianRandomChange(float[] newWeights, float[] originalWeights){
        Random random = new Random();
        for (int i = 0; i < originalWeights.length; i++) {
                newWeights[i] = (float) (originalWeights[i] + random.nextGaussian());
        }
    }

    private void initializeWithRandom(float[][] weights){
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = (float) (random.nextFloat()*0.4 - 0.2);
            }
        }
    }

    private void initializeWithRandom(float[] weights){
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) (random.nextFloat()*0.4 - 0.2);
        }
    }

    private float[] getInputArray(CheckersBoard checkersBoard){
        float[] input = new float[inputNodes];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if(checkersBoard.board[i][j] == Piece.WHITE_PAWN)
                        input[index] = 1;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_PAWN)
                        input[index] = -1;
                    else if(checkersBoard.board[i][j] == Piece.WHITE_KING)
                        input[index] = kingValue;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_KING)
                        input[index] = -1*kingValue;
                    else
                        input[index] = 0;
                }
            }
        }
        return input;
    }

    private static float[] activationFunction(float[] A){
        float[] result = new float[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = (float) Math.tanh(A[i]);
        }
        return result;
    }

    public static float activationFunction(float x){
        return (float) Math.tanh(x);
    }

    private static float[] multiply(float[] A, float[][] B){
        float[] P = new float[B[0].length];
        for(int i=0; i<B[0].length; i++){
            float sum = 0;
            for (int j = 0; j < A.length; j++) {
                sum += A[j]*B[j][i];
            }
            P[i] = sum;
        }
        return P;
    }

    private static float multiply(float[] A, float[] B){
        float product = 0;
        for (int i = 0; i < A.length; i++) {
            product += A[i]*B[i];
        }
        return product;
    }
}
