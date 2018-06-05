package castles.fiefdom

import castles.BaseSpec

class FortressSpec extends BaseSpec {
  val startingCastleHeight: Int = 30
  val startingWallHeight: Int = 30
  val startingFortress = Fortress(startingCastleHeight, startingWallHeight)

  "ResourceProducer" should {

    "build castle by passed integer" in {
      val countToAdd = 100
      Fortress.buildCastle(startingFortress, countToAdd).castleHeight shouldEqual startingCastleHeight + countToAdd
    }

    "build wall by passed integer" in {
      val countToAdd = 100
      Fortress.buildWall(startingFortress, countToAdd).wallHeight shouldEqual startingWallHeight + countToAdd
    }

    "takeCastleDamage to castle only" in {
      val damageCount = 5
      Fortress.takeCastleDamage(startingFortress, damageCount) shouldEqual Fortress(startingCastleHeight - damageCount, startingWallHeight)
    }

    "takeDamage to wall only if damage is less than wall height" in {
      val damageCount = 5
      Fortress.takeDamage(startingFortress, damageCount) shouldEqual Fortress(startingCastleHeight, startingWallHeight - damageCount)
    }

    "takeDamage overflowing to castle after wall is consumed" in {
      val damageCount = 50
      Fortress.takeDamage(startingFortress, damageCount) shouldEqual Fortress(startingCastleHeight + startingWallHeight - damageCount, 0)
    }

    "takeDamage to zero, but not below" in {
      val damageCount = 10000
      Fortress.takeDamage(startingFortress, damageCount) shouldEqual Fortress(0, 0)
    }
  }
}