package castles.fiefdom

import castles.BaseSpec

class CastleSpec extends BaseSpec {
  val startingTowerHeight: Int = 30
  val startingWallHeight: Int = 30
  val startingCastle = Castle(startingTowerHeight, startingWallHeight)

  "ResourceProducer" should {

    "build castle by passed integer" in {
      val countToAdd = 100
      Castle.buildCastle(startingCastle, countToAdd).towerHeight shouldEqual startingTowerHeight + countToAdd
    }

    "build wall by passed integer" in {
      val countToAdd = 100
      Castle.buildWall(startingCastle, countToAdd).wallHeight shouldEqual startingWallHeight + countToAdd
    }

    "takeCastleDamage to castle only" in {
      val damageCount = 5
      Castle.takeCastleDamage(startingCastle, damageCount) shouldEqual Castle(startingTowerHeight - damageCount, startingWallHeight)
    }

    "takeDamage to wall only if damage is less than wall height" in {
      val damageCount = 5
      Castle.takeDamage(startingCastle, damageCount) shouldEqual Castle(startingTowerHeight, startingWallHeight - damageCount)
    }

    "takeDamage overflowing to castle after wall is consumed" in {
      val damageCount = 50
      Castle.takeDamage(startingCastle, damageCount) shouldEqual Castle(startingTowerHeight + startingWallHeight - damageCount, 0)
    }

    "takeDamage to zero, but not below" in {
      val damageCount = 10000
      Castle.takeDamage(startingCastle, damageCount) shouldEqual Castle(0, 0)
    }
  }
}