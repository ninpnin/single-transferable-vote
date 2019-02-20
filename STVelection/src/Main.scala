import scala.collection.mutable.Buffer
import scala.io.Source

object Main extends App {

	val voters = Source.fromFile("STVElection/resources/votes.txt")
		.getLines
		.toVector
		.map(x => new Voter(x.split(",").map(y => y.toInt).toBuffer))
		.toBuffer

	println(voters)

	val election = new STV(voters, 3)
	val electorate = election.calculateWinners
	println("Winners are " + electorate.map(_.toString).reduce(_ + ", " + _))
}