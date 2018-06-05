package castles.fiefdom

final case class ResourceProducer(creatorCount: Int, inventoryCount: Int)

object ResourceProducer {
  private def adjustInventory(startingProducer: ResourceProducer, changeCount: Int): ResourceProducer = {
    val newInventoryCount = startingProducer.inventoryCount + changeCount
    assert(newInventoryCount >= 0, "Tried to consume more of a resource than possible.")
    startingProducer.copy(inventoryCount = newInventoryCount)
  }

  def produce(startingProducer: ResourceProducer): ResourceProducer = {
    adjustInventory(startingProducer, startingProducer.creatorCount)
  }

  def add(startingProducer: ResourceProducer, addCount: Int): ResourceProducer = {
    adjustInventory(startingProducer, addCount)
  }

  def consume(startingProducer: ResourceProducer, consumeCount: Int): ResourceProducer = {
    adjustInventory(startingProducer, -consumeCount)
  }
}