package castles.fiefdom

import castles.game.WinConditions

final case class Fiefdom(castle: Castle, barracks: ResourceProducer, mine: ResourceProducer, temple: ResourceProducer)

object Fiefdom {
  def hasWon(fiefdom: Fiefdom, winConditions: WinConditions): Boolean =
    fiefdom.castle.towerHeight >= winConditions.winningTowerHeight ||
      (fiefdom.barracks.inventoryCount >= winConditions.winningResourceCount
        && fiefdom.mine.inventoryCount >= winConditions.winningResourceCount
        && fiefdom.temple.inventoryCount >= winConditions.winningResourceCount)

  def hasLost(fiefdom: Fiefdom): Boolean = fiefdom.castle.towerHeight <= 0
}