package me.sivieri.aoc2022.day6

class CommunicationSystem(
    private val input: String
) {

    @Suppress("KotlinConstantConditions")
    fun countPremarkerData(size: Int): Int {
        val windows = input.windowed(size, 1)
        val (counter, _) = windows.fold(Pair(0, false)) { (counter, found), s ->
            if (!found) {
                if (s.toList().distinct().size == size) Pair(counter + size, true)
                else Pair(counter + 1, found)
            }
            else Pair(counter, found)
        }
        return counter
    }

}