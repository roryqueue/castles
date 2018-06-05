package castles.fiefdom

import castles.BaseSpec
import castles.game.WinConditions

class FiefdomSpec extends BaseSpec {
  val defaultResourceCount: Int = 30
  val defaultCreatorCount: Int = 3
  val defaultTowerHeight: Int = 30
  val defaultWallHeight: Int = 30
  val defaultCastle = Castle(defaultTowerHeight, defaultWallHeight)
  val defaultResourceProducer = ResourceProducer(defaultCreatorCount, defaultResourceCount)
  val defaultFiefdom = Fiefdom(defaultCastle, defaultResourceProducer, defaultResourceProducer, defaultResourceProducer)

  "Fiefdom" should {

    "win a game when its tower height meets the win condition height" in {
      Fiefdom.hasWon(defaultFiefdom, WinConditions(30, 20)) shouldEqual true
    }

    "win a game when all its resources meet or exceed the win condition inventory" in {
      Fiefdom.hasWon(defaultFiefdom, WinConditions(20, 30)) shouldEqual true
    }

    "not win a game when it meets no win conditions" in {
      Fiefdom.hasWon(defaultFiefdom, WinConditions(20, 20)) shouldEqual false
    }

    "not win a game when some but not all resources meet the win condition inventory" in {
      Fiefdom.hasWon(defaultFiefdom.copy(mine = ResourceProducer(defaultCreatorCount, 40)), WinConditions(20, 35)) shouldEqual false
    }

    "not lose a game when its tower height is greater than zero" in {
      Fiefdom.hasLost(defaultFiefdom) shouldEqual false
    }

    "lose a game when its tower height is zero" in {
      Fiefdom.hasLost(defaultFiefdom.copy(castle = Castle(0, defaultWallHeight))) shouldEqual true
    }
  }
}