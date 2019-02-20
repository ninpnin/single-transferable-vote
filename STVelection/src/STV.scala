import scala.collection.mutable.Buffer

class STV(voters: Buffer[Voter], seats: Int) {
	
	def quota(totalVotes: Int, seats: Int) = {
		totalVotes.toDouble / seats
	}
	def calculateWinners() = {
		println("Winners are calculated...")

		def firstVotes = voters.filter(_.rank.size > 0).map(_.rank(0))
		def allVotes = voters.flatMap(_.rank)

		// Eliminate those who got 0 first votes
		val notFirst = allVotes.distinct -- firstVotes.distinct
		println("notfirst",notFirst)
		for (voter <- voters) {
			for (c <- notFirst)
				voter.removeCandidate(c)
		}

		while (firstVotes.distinct.size > seats) {

			val firstCounts = (firstVotes zip firstVotes)
				.map(x => (x._1, firstVotes.filter(_ == x._2).size) )
				.distinct
				.sortBy(- _._2)

			val currentQuota = quota(voters.size, seats)

			println(firstCounts)
			println(firstVotes)
			println(currentQuota)

			val eliminate = firstCounts.last._1

			for (voter <- voters) {
				voter.removeCandidate(eliminate)
			}

		}

		firstVotes.distinct
	}
}