/**
 * Test cases for SpatialTemporalDatabase.scala.
 *
 * @author  Yujian Zhang <yujian{dot}zhang[at]gmail(dot)com>
 *
 * License:
 *   GNU General Public License v2
 *   http://www.gnu.org/licenses/gpl-2.0.html
 * Copyright (C) 2018 Yujian Zhang
 */

import net.whily.hgc.{Place, PlaceType, River, SpatialTemporalDatabase}
import org.scalatest._

class SpatialTemporalDatabaseTest extends FunSpec with Matchers {
  describe("Test sample database") {
    val places = """
    漢靈帝中平二年 add 洛阳 34.631514 112.454681 Capital   // To be confirmed
        add 长安 34.266667 108.9 Prefecture
        add 武關 33.71 110.35 Town
    唐高祖武德二年 clear
        add 邕州 22.8161 108.3664 Prefecture
        add 柳州 24.3262 109.4284 Prefecture
        add 西受降城 40.765961 107.391206 Town
    公元二〇〇〇年 ren 邕州 南寧
        del  西受降城
        update 柳州 24.3262 110.3 Prefecture

    """
    val database = new SpatialTemporalDatabase(places, River.rivers)

    it("Check database") {
      database.length should be (3)
    }

    it("Check 漢靈帝中平二年") {
      val snapshot = database(0).snapshot
      val places = snapshot.places
      places.size should be (3)
      val place1 = places("洛阳")
      val place2 = places("长安")
      val place3 = places("武關")
      (place1 ≈ Place(34.631514, 112.454681, PlaceType.Capital)) should be (true)
      (place2 ≈ Place(34.266667, 108.9, PlaceType.Prefecture)) should be (true)
      (place3 ≈ Place(33.71, 110.35, PlaceType.Town)) should be (true)
    }

    it("Check 唐高祖武德二年") {
      val snapshot = database(1).snapshot
      val places = snapshot.places
      places.size should be (3)
      val place1 = places("邕州")
      val place2 = places("柳州")
      val place3 = places("西受降城")
      (place1 ≈ Place(22.8161, 108.3664, PlaceType.Prefecture)) should be (true)
      (place2 ≈ Place(24.3262, 109.4284, PlaceType.Prefecture)) should be (true)
      (place3 ≈ Place(40.765961, 107.391206, PlaceType.Town)) should be (true)
    }

    it("Check 公元二〇〇〇年") {
      val snapshot = database(2).snapshot
      val places = snapshot.places
      places.size should be (2)
      val place1 = places("南寧")
      val place2 = places("柳州")
      (place1 ≈ Place(22.8161, 108.3664, PlaceType.Prefecture)) should be (true)
      (place2 ≈ Place(24.3262, 110.3, PlaceType.Prefecture)) should be (true)
    }
  }
}
