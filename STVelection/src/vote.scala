import scala.collection.mutable.Buffer

class Voter(val rank: Buffer[Int]) {
	
	def removeCandidate(candNo: Int) = {
		if (this.rank.contains(candNo)) {
			this.rank -= candNo
		}
	}
	override def toString() = {
		"Vote : " + rank
	}
}