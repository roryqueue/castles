package castles.fiefdom

import castles.BaseSpec

class ResourceSpec extends BaseSpec {
  val startingCreators: Int = 3
  val startingInventory: Int = 25
  val startingProducer = ResourceProducer(startingCreators, startingInventory)

  "ResourceProducer" should {
    "produce a resource by adding one for each creator" in {
      ResourceProducer.produce(startingProducer) shouldEqual ResourceProducer(startingCreators, startingInventory + startingCreators)
    }

    "add a resource by passed integer" in {
      val countToAdd = 100
      ResourceProducer.add(startingProducer, countToAdd) shouldEqual ResourceProducer(startingCreators, startingInventory + countToAdd)
    }

    "consume a resource by passed integer" in {
      val countToConsume = 5
      ResourceProducer.add(startingProducer, countToConsume) shouldEqual ResourceProducer(startingCreators, startingInventory - countToConsume)
    }

    "throw if it attempts to consume more of its resource than it has in inventory" in {
      val tooMuchToConsume = startingInventory + 1
      assertThrows(ResourceProducer.add(startingProducer, tooMuchToConsume))
    }
  }
}