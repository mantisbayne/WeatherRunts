package com.mantisbayne.weatherrunts.utils

object TempUtils {

    fun mapTempsToTimes(temperatures: List<Int>, times: List<String>) =
        mutableListOf<Pair<Int, String>>().apply {
            temperatures.forEachIndexed { index, temp ->
                add(temp to times[index])
            }
        }

    fun mapTempsToTimeOfDay(tempToTimes: List<Pair<Int, String>>): Map<TimeOfDay, MutableList<Int>> {
        val timeOfDayToTemps = linkedMapOf<TimeOfDay, MutableList<Int>>(
//            TimeOfDay.MORNING to mutableListOf(),
//            TimeOfDay.MIDDAY to mutableListOf(),
//            TimeOfDay.EVENING to mutableListOf(),
//            TimeOfDay.NIGHT to mutableListOf()
        )

        tempToTimes.map {
            mapTempToTimeOfDay(it)
        }.forEach { (timeOfDay, temp) ->
            val temperaturesForTimeOfDay = timeOfDayToTemps[timeOfDay] ?: mutableListOf()
            temperaturesForTimeOfDay.add(temp)
            timeOfDayToTemps[timeOfDay] = temperaturesForTimeOfDay
        }

        return timeOfDayToTemps
    }

    private fun mapTempToTimeOfDay(tempToTime: Pair<Int, String>): Pair<TimeOfDay, Int> {
        val hour = DateUtils.getHourOfDay(tempToTime.second)

        val timeOfDay = when (hour) {
            in 4..11 -> TimeOfDay.MORNING
            in 12..17 -> TimeOfDay.MIDDAY
            in 17..22 -> TimeOfDay.EVENING
            else -> TimeOfDay.NIGHT
        }

        return timeOfDay to tempToTime.first
    }
}

enum class TimeOfDay {
    MORNING,
    MIDDAY,
    EVENING,
    NIGHT
}
