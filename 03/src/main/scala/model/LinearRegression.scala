package model

import breeze.linalg.{DenseVector, DenseMatrix}


class LinearRegression {
  var weights: DenseVector[Double] = DenseVector.zeros[Double](size = 0)
  
  def fit(values: DenseMatrix[Double], outputs: DenseVector[Double]): Unit = {
    weights = (values.t * values) \ (values.t * outputs)
  }
  def predict(values: DenseMatrix[Double]): DenseVector[Double] = {
    values * weights
  }
}