# lenskit-hello-scala #

Scala port of [lenskit-hello](https://bitbucket.org/grouplens/lenskit-hello), the demo project for the [LensKit Recommender Toolkit](http://lenskit.grouplens.org/).

## Usage ##

To get recommendations for users 1, 2 and 3 for ratings stored in the tab-separated ratings.dat:

`sbt "run ratings.dat 1 2 3"`

To get recommendations for users 1, 2 and 3 for ratings stored in the comma-separated ratings.csv:

`sbt "run -d , ratings.dat 1 2 3"`