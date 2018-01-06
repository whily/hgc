/**
 * Spatial Temporal Database.
 *
 * @author  Yujian Zhang <yujian{dot}zhang[at]gmail(dot)com>
 *
 * License:
 *   GNU General Public License v2
 *   http://www.gnu.org/licenses/gpl-2.0.html
 * Copyright (C) 2017-2018 Yujian Zhang
 */

package net.whily.hgc

import scala.collection.mutable.{HashMap, MutableList, LinearSeq}
import net.whily.chinesecalendar.ChineseCalendar

/**
  * The snapshot of the databased at one time instant.
  *
  * @param places     An array of places
  * @param rivers     An array of rivers
  */
case class TemporalSnapshot(places: HashMap[String, Place], rivers: Array[River]) {
  /** Equals method. */
  override def equals(other: Any): Boolean = other match {
    case that: TemporalSnapshot => places == that.places &&
      rivers == that.rivers
    case _ => false
  }
}

case class SnapshotRecord(val date: ChineseCalendar, val snapshot: TemporalSnapshot) {
}

/**
  * SpatialTemporalDatabase stores historical geography data.
  *
  * @param records       A multi-line string of records in chronologically increasing order.
  * @param rivers        An array of rivers. (TODO: to allow rivers varying as places as well.
  *
  * There can be comments starting from `//` in the records.
  * Each record is in the following format with leading/trailing spaces ignored
  *
  *   [date] operation
  *
  * When date is optional, the latest date in previous records are assumed.
  *
  * Operation are as follows:
  *
  *   add name latitude longitude type     - Add an entry
  *   clear                                - Remove all entries
  *   del name                             - Remove an entry
  *   update name latitude longitude type  - Update an entry
  *   ren old-name new-name                - Rename an entry
  *
  * Field name is in format a|b|c with increasing scope.
  */
class SpatialTemporalDatabase(records: String, val rivers: Array[River])
  extends LinearSeq[SnapshotRecord] {
  private var currentDate: ChineseCalendar = null
  private var currentSnapshot: TemporalSnapshot = null
  private var snapshots = MutableList[SnapshotRecord]()

  private def removeComments(str: String) = {
    val i = str.indexOf("//")
    var s = str
    if (i >= 0)
      s = str.substring(0, i)
    s.trim
  }

  private val strs = records.split("\n").map(removeComments).filter(_.length() > 0)

  private val operations = Array("add", "clear", "del", "update", "ren")

  for (str <- strs) {
    val fields = str.split(" ").filter(_.length() > 0)
    assert((fields != null) && (!fields.isEmpty) && (fields.length >= 2))
    val dateFirst = !operations.contains(fields(0))

    if (dateFirst) {
      val date = ChineseCalendar.parseDate(fields(0))
      val operation = fields(1)
      assert(operations.contains(operation))
      if (currentDate == null) {
        // This is the first record.
        currentSnapshot = TemporalSnapshot(HashMap[String, Place](), River.rivers)
      } else {
        assert(ChineseCalendar.toDate(date, false) > ChineseCalendar.toDate(currentDate, false))
        snapshots += SnapshotRecord(currentDate, currentSnapshot)

        if (operation == "clear") {
          currentSnapshot = TemporalSnapshot(HashMap[String, Place](), River.rivers)
        } else {
          currentSnapshot = TemporalSnapshot(currentSnapshot.places.clone(), River.rivers)
        }
      }
      currentDate = date
      if (fields(1) != "clear") {
        processRecord(fields.drop(1))
      }
    } else {
      assert((currentDate != null) && (fields(0) != "clear"))
      processRecord(fields)
    }
  }

  snapshots += SnapshotRecord(currentDate, currentSnapshot)

  // Process one record.
  // The first field of record is the operation (add, del etc).
  // Modify currentSnapshot directly.
  private def processRecord(record: Array[String]) {
    val operation = record(0)
    assert(operations.contains(operation))
    operation match {
      case "add" | "update" =>
        currentSnapshot.places(record(1)) = Place(record(2).toDouble, record(3).toDouble,
          SpatialTemporalDatabase.toPlaceType(record(4)))
      case "del" =>
        currentSnapshot.places -= record(1)
      case "ren" =>
        val oldName = record(1)
        val newName = record(2)
        val value = currentSnapshot.places(oldName)
        currentSnapshot.places(newName) = value
        currentSnapshot.places -= oldName
      case "clear" =>
    }
  }

  def apply(idx: Int) = snapshots(idx)

  def length = snapshots.length

  def update(idx: Int, elem: SnapshotRecord) {
    snapshots(idx) = elem
  }

  /** Returns the temporal snapshot for the given date. */
  def snapshot(date: ChineseCalendar): TemporalSnapshot = {
    // TODO: placeholder.
    new TemporalSnapshot(null, rivers)
  }
}

object SpatialTemporalDatabase {
  def toPlaceType(s: String) = s match {
    case "Capital"    => PlaceType.Capital
    case "Province"   => PlaceType.Province
    case "Prefecture" => PlaceType.Prefecture
    case "County"     => PlaceType.County
    case "Town"       => PlaceType.Town
  }
  val chinaDatabase = new SpatialTemporalDatabase(Place.places, River.rivers)
}
