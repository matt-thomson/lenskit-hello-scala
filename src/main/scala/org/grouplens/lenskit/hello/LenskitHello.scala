package org.grouplens.lenskit.hello

import org.grouplens.lenskit.baseline._
import org.grouplens.lenskit.core.{LenskitRecommender, LenskitConfiguration}
import org.grouplens.lenskit.cursors.Cursors
import org.grouplens.lenskit.data.dao._
import org.grouplens.lenskit.knn.item.ItemItemScorer
import org.grouplens.lenskit.transform.normalize._
import org.grouplens.lenskit.ItemScorer

import java.io.File
import scala.collection.JavaConversions._

object LenskitHello {
  def main(args: Array[String]) {
    if (args(0) == "-d") run(args(1), args drop 2) else run("\t", args)
  }

  def run(delimiter: String, args: Array[String]) {
    val userIds = args drop 1 map (s => s.toLong)
    run(delimiter, args(0), userIds)
  }

  def run(delimiter: String, fileName: String, userIds: Array[Long]) {
    val base = new SimpleFileRatingDAO(new File(fileName), delimiter)
    val dao = new EventCollectionDAO(Cursors.makeList(base.streamEvents))

    val config = new LenskitConfiguration()
    config.bind(classOf[EventDAO]).to(dao)
    config.bind(classOf[ItemScorer]).to(classOf[ItemItemScorer])
    config.bind(classOf[BaselineScorer], classOf[ItemScorer]).to(classOf[UserMeanItemScorer])
    config.bind(classOf[UserMeanBaseline], classOf[ItemScorer]).to(classOf[ItemMeanRatingItemScorer])
    config.bind(classOf[UserVectorNormalizer]).to(classOf[BaselineSubtractingUserVectorNormalizer])

    val recommender = LenskitRecommender.build(config).getItemRecommender
    userIds map (id => {
      println(s"Recommendations for user $id:")
      recommender.recommend(id, 10) map (rec => println(s"\t${rec.getId}"))
    })
  }
}
