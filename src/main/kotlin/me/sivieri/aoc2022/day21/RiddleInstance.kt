package me.sivieri.aoc2022.day21

class RiddleInstance(
    val label: String,
    var value: Long?,
    val operation: RiddleOperation,
    var side: RiddleSide? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RiddleInstance

        return label == other.label
    }

    override fun hashCode(): Int {
        return label.hashCode()
    }

    override fun toString(): String {
        return "RiddleInstance(label='$label', value=$value, operation=$operation, side=$side)"
    }

}
