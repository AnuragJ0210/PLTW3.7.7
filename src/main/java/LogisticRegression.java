// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// public class LogisticRegression {
//   private double[] weights;
//   private double learningRate;
//   private int numFeatures;

//   public LogisticRegression(int numFeatures, double learningRate) {
//     this.numFeatures = numFeatures;
//     this.learningRate = learningRate;
//     this.weights = new double[numFeatures + 1]; // Additional weight for bias
//   }

//   // public void train(String filename, int[] columnsToUse, int labelColumnIndex,
//   // int numIterations) throws IOException {
//   // BufferedReader br = new BufferedReader(new FileReader(filename));
//   // String line;
//   // int rowCount = 0;

//   // while ((line = br.readLine()) != null) {
//   // if (rowCount > 0) {
//   // String[] parts = line.split(",");
//   // double[] features = new double[numFeatures];
//   // for (int i = 0; i < numFeatures; i++) {
//   // features[i] = Double.parseDouble(parts[columnsToUse[i]]);
//   // }
//   // int label;
//   // if (parts[labelColumnIndex].equals("True")) {
//   // label = 1;
//   // } else if (parts[labelColumnIndex].equals("False")) {
//   // label = 0;
//   // } else {
//   // throw new IllegalArgumentException("Invalid label value: " +
//   // parts[labelColumnIndex]);
//   // }

//   // trainSingleExample(features, label);
//   // System.out.println(rowCount);

//   // }
//   // rowCount++;
//   // }
//   // br.close();
//   // }

//   public void train(String filename, int[] columnsToUse, int labelColumnIndex, int numIterations) throws IOException {
//     for (int iter = 0; iter < numIterations; iter++) {
//       BufferedReader br = new BufferedReader(new FileReader(filename));
//       String line;
//       int rowCount = 0;

//       while ((line = br.readLine()) != null) {
//         if (rowCount > 0) {
//           String[] parts = line.split(",");
//           double[] features = new double[numFeatures];
//           for (int i = 0; i < numFeatures; i++) {
//             features[i] = Double.parseDouble(parts[columnsToUse[i]]);
//           }
//           int label;
//           if (parts[labelColumnIndex].equals("True")) {
//             label = 1;
//           } else if (parts[labelColumnIndex].equals("False")) {
//             label = 0;
//           } else {
//             throw new IllegalArgumentException("Invalid label value: " + parts[labelColumnIndex]);
//           }

//           trainSingleExample(features, label);
//         }
//         rowCount++;
//       }
//       br.close();
//     }
//   }

//   private void trainSingleExample(double[] features, int label) {
//     double predicted = predict(features);
//     double error = label - predicted;
//     for (int i = 0; i < numFeatures; i++) {
//       weights[i] -= learningRate * error * features[i];
//       // System.out.println(weights[i]);
//     }
//     // Update bias weight
//     weights[numFeatures] -= learningRate * error;
//   }

//   public double predict(double[] features) {
//     double sum = weights[numFeatures]; // Bias
//     for (int i = 0; i < numFeatures; i++) {
//       sum += weights[i] * features[i];
//     }
//     return sigmoid(sum);
//   }

//   private double sigmoid(double z) {
//     return 1.0 / (1.0 + Math.exp(-z));
//   }

//   public static void main(String[] args) {
//     try {
//       int numFeatures = 8; // Change this according to the number of features you want to use
//       double learningRate = 0.0001;
//       int numIterations = 10;
//       int[] columnsToUse = { 4, 5, 6, 7, 8, 9, 10, 11 }; // Specify the indices of the columns you want to use for
//                                                          // features
//       int labelColumnIndex = 12; // Specify the index of the column containing the labels

//       LogisticRegression logisticRegression = new LogisticRegression(numFeatures, learningRate);
//       logisticRegression.train("Pokemon.csv", columnsToUse, labelColumnIndex, numIterations);

//       // Example usage
//       double[] testFeatures = { 0, 0, 0, 0, 500, 0, 0, 0 }; // Example test features
//       double prediction = logisticRegression.predict(testFeatures);
//       System.out.println("Prediction: " + prediction);
//     } catch (IOException e) {
//       e.printStackTrace();
//     }
//   }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LogisticRegression {
  private double[] weights;
  private double learningRate;
  private int numFeatures;
  private double lambda; // Regularization parameter

  public LogisticRegression(int numFeatures, double learningRate, double lambda) {
    this.numFeatures = numFeatures;
    this.learningRate = learningRate;
    this.lambda = lambda;
    this.weights = new double[numFeatures + 1]; // Additional weight for bias
    // Initialize weights to 0
    Arrays.fill(weights, 0);
  }

  public void train(String filename, int[] columnsToUse, int labelColumnIndex, int numIterations) throws IOException {
    for (int iter = 0; iter < numIterations; iter++) {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line;
      int rowCount = 0;

      while ((line = br.readLine()) != null) {
        if (rowCount > 0) {
          String[] parts = line.split(",");
          double[] features = new double[numFeatures];
          for (int i = 0; i < numFeatures; i++) {
            features[i] = Double.parseDouble(parts[columnsToUse[i]]);
          }
          int label;
          if (parts[labelColumnIndex].equals("True")) {
            label = 1;
          } else if (parts[labelColumnIndex].equals("False")) {
            label = 0;
          } else {
            throw new IllegalArgumentException("Invalid label value: " + parts[labelColumnIndex]);
          }

          trainSingleExample(features, label);
        }
        rowCount++;
      }
      br.close();
    }
  }

  private void trainSingleExample(double[] features, int label) {
    double predicted = predict(features);
    double error = label - predicted;
    for (int i = 0; i < numFeatures; i++) {
      weights[i] += learningRate * (error * features[i]); // Regularization term added
      System.out.println("Error: " + error);
      System.out.println("Label: " + label);
    }
    // Update bias weight
    weights[numFeatures] += learningRate*error;
  }

  public double predict(double[] features) {
    double sum = weights[numFeatures]; // Bias
    for (int i = 0; i < numFeatures; i++) {
      sum += weights[i] * features[i];
    }
    return sigmoid(sum);
  }

  private double sigmoid(double z) {
    return 1.0 / (1.0 + Math.exp(-z));
  }

  public static void main(String[] args) {
    try {
      int numFeatures = 8; // Change this according to the number of features you want to use
      double learningRate = 0.001;
      int numIterations = 100;
      double lambda = 10; // Regularization parameter
      int[] columnsToUse = {4, 5, 6, 7, 8, 9, 10, 11}; // Specify the indices of the columns you want to use for
                                                         // features
      int labelColumnIndex = 13; // Specify the index of the column containing the labels

      LogisticRegression logisticRegression = new LogisticRegression(numFeatures, learningRate, lambda);
      logisticRegression.train("Pokemon.csv", columnsToUse, labelColumnIndex, numIterations);

      // Example usage
      double[] testFeatures = {0, 0, 0, 0, 500, 0, 0, 0}; // Example test features
      double prediction = logisticRegression.predict(testFeatures);
      System.out.println("Prediction: " + prediction);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}