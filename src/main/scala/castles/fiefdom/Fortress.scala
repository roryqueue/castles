package castles.fiefdom

final case class Fortress(castleHeight: Int, wallHeight: Int)

object Fortress {
  def buildWall(startingFortress: Fortress, buildCount: Int): Fortress = {
    startingFortress.copy(wallHeight = startingFortress.castleHeight + buildCount)
  }

  def buildCastle(startingFortress: Fortress, buildCount: Int): Fortress = {
    startingFortress.copy(castleHeight = startingFortress.castleHeight + buildCount)
  }

  def takeCastleDamage(startingFortress: Fortress, damageCount: Int): Fortress = {
    val heightAfterDamage = startingFortress.castleHeight - damageCount
    startingFortress.copy(castleHeight = if (heightAfterDamage < 0) 0 else heightAfterDamage)
  }

  def takeDamage(startingFortress: Fortress, damageCount: Int): Fortress = {
    if (damageCount < startingFortress.wallHeight) {
      val castleDamage = damageCount - startingFortress.wallHeight
      takeCastleDamage(startingFortress.copy(wallHeight = 0), castleDamage)
    } else {
      startingFortress.copy(wallHeight = startingFortress.wallHeight - damageCount)
    }
  }
}