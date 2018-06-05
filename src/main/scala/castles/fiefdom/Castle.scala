package castles.fiefdom

final case class Castle(towerHeight: Int, wallHeight: Int)

object Castle {
  def buildWall(startingCastle: Castle, buildCount: Int): Castle = {
    startingCastle.copy(wallHeight = startingCastle.towerHeight + buildCount)
  }

  def buildCastle(startingCastle: Castle, buildCount: Int): Castle = {
    startingCastle.copy(towerHeight = startingCastle.towerHeight + buildCount)
  }

  def takeCastleDamage(startingCastle: Castle, damageCount: Int): Castle = {
    val heightAfterDamage = startingCastle.towerHeight - damageCount
    startingCastle.copy(towerHeight = if (heightAfterDamage < 0) 0 else heightAfterDamage)
  }

  def takeDamage(startingCastle: Castle, damageCount: Int): Castle = {
    if (damageCount < startingCastle.wallHeight) {
      val castleDamage = damageCount - startingCastle.wallHeight
      takeCastleDamage(startingCastle.copy(wallHeight = 0), castleDamage)
    } else {
      startingCastle.copy(wallHeight = startingCastle.wallHeight - damageCount)
    }
  }
}