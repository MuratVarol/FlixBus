package io.varol.flixbus.util.common

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.util.Log
import io.varol.flixbus.data.models.DateTime
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by varol on 26.4.2018.
 */

val dateFormat = "MM/dd/yyyy HH:MM:ss"


/**
 * Reified method for convert DateTime object to calculated time
 */
fun DateTime.toStationTime(): String {
    return longToTime(this)
}

/**
 * Takes {DateTime} object as parameters
 * Add TimeZone offset to long date value and
 * @return Time as String
 */
@SuppressLint("SimpleDateFormat")
private fun longToTime(dateTime: DateTime): String {

    try {
        // set DateTime object's timezone and set it to stationZoneId
        val stationZoneId = TimeZone.getTimeZone(dateTime.tz.toString())

        // convert timestamp to string formatted date
        var dateString = DateFormat.format(dateFormat, Date(dateTime.timestamp * 1000L)).toString()
        Log.i("first date :", dateString)

        // sum UTC timestamp with station time zone offset
        val dateWithOffsetLong: Long = (SimpleDateFormat(dateFormat).parse(dateString).time + stationZoneId.rawOffset)

        dateString = DateFormat.format(dateFormat, Date(dateWithOffsetLong)).toString()

        Log.i("second date+ offset :", dateString)

        // return calculated datetime's time as string
        val timeFormat = "HH:MM"
        val timeString = DateFormat.format(timeFormat, Date(dateWithOffsetLong)).toString()
        Log.i("only time :", timeString)
        return timeString

    } catch (ex: Exception) {
        return "Error"
    }

}
