import breeze.linalg.{DenseMatrix, DenseVector, csvread, csvwrite}
import model.LinearRegression
import packages.tools.{crossValidation}
import packages.logger.{getLogger}
import java.io.File


object Main {
  def main(args: Array[String]): Unit = {
    val trainPath: File = new File ("src/main/data/train.csv")
    val testPath: File = new File ("src/main/data/test.csv")
    val outputPath: File = new File ("src/main/data/predictions.csv")

    val logger = getLogger(name = "Logistic Regression", outputPath = "src/main/logs/app.log")

    val model = new LinearRegression()
    val train: DenseMatrix[Double] = csvread(trainPath, separator=',')
    val xTrain: DenseMatrix[Double] = train(::, 0 to -2)
    val yTrain: DenseVector[Double] = train(::, -1)
    
    crossValidation(model, train, numFolds = 10, logger = logger)
    model.fit(xTrain, yTrain)

    val test: DenseMatrix[Double] = csvread(testPath, separator = ',')
    val testPredictions: DenseVector[Double] = model.predict(test)

    csvwrite(outputPath, testPredictions.asDenseMatrix.t)
    logger.info(s"Predictions saved to $outputPath")
  }
}