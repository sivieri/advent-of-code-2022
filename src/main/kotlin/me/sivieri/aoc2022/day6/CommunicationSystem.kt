package me.sivieri.aoc2022.day6

class CommunicationSystem(
    private val input: String
) {

    @Suppress("KotlinConstantConditions")
    fun countPremarkerData(): Int {
        val windows = input.windowed(4, 1)
        val (counter, _) = windows.fold(Pair(0, false)) { (counter, found), s ->
            if (!found) {
                if (s.toList().distinct().size == 4) Pair(counter + 4, true)
                else Pair(counter + 1, found)
            }
            else Pair(counter, found)
        }
        return counter
    }

}