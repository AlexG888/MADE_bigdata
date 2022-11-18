package packages

import java.util.logging.{Logger,FileHandler, SimpleFormatter}


package object logger {
  def getLogger(name: String, outputPath: String): Logger = {
    System.setProperty(
      "java.util.logging.SimpleFormatter.format",
      "%1$tF %1$tT %4$s %5$s%6$s%n"
    )
    val logger = Logger.getLogger(name)
    val handler = new FileHandler(outputPath)
    val formatter = new SimpleFormatter()
    handler.setFormatter(formatter)
    logger.addHandler(handler)
    logger
  }
}