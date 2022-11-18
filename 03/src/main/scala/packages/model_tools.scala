package packages

import breeze.linalg.{DenseVector, DenseMatrix}
import model.LinearRegression
import breeze.numerics.abs
import breeze.stats.mean
import java.util.logging.{Logger}


package object tools {
  def mae(Y: DenseVector[Double], y: DenseVector[Double]): Double = {
    mean(abs(Y - y))
  }
  def crossValidation(model: LinearRegression, data: DenseMatrix[Double], 
                      numFolds: Int, logger: Logger): Unit = {
    val step: Int = data.rows / numFolds
    for (i <- 0 until numFolds) {
      val trainIdxs: IndexedSeq[Int] = IndexedSeq.range(0, i * step) ++ IndexedSeq.range((i + 1) * step, data.rows)
      val validIdxs: IndexedSeq[Int] = IndexedSeq.range(i * step, (i + 1) * step)

      val trainFold: DenseMatrix[Double] = data(trainIdxs, ::).toDenseMatrix
      val xTrain: DenseMatrix[Double] = trainFold(::, 0 to -2)
      val yTrain: DenseVector[Double] = trainFold(::, -1)

      val validFold: DenseMatrix[Double] = data(validIdxs, ::).toDenseMatrix
      val xValid: DenseMatrix[Double] = validFold(::, 0 to -2)
      val yValid: DenseVector[Double] = validFold(::, -1)

      model.fit(xTrain, yTrain)

      val maeScore: Double = mae(yValid, model.predict(xValid))
      logger.info(s"Fold $i, MAE-score: $maeScore")
    }
  }
}


