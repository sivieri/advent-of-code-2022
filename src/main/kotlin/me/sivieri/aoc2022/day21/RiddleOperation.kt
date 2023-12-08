package me.sivieri.aoc2022.day21

sealed class RiddleOperation {
    abstract fun execute(x: Long, y: Long): Long
    abstract fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long

    companion object {
        fun operationFromSymbol(symbol: Char): RiddleOperation = when (symbol) {
            '+' -> SumOperation
            '-' -> SubtractionOperation
            '*' -> ProductOperation
            '/' -> DivisionOperation
            else -> NoOp
        }
    }
}

data object SumOperation : RiddleOperation() {
    override fun execute(x: Long, y: Long): Long = x + y
    override fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long = when {
        x != null && y == null -> z - x
        x == null && y != null -> z - y
        else -> throw IllegalArgumentException("One value needs to be null and the other not")
    }
}

data object SubtractionOperation : RiddleOperation() {
    override fun execute(x: Long, y: Long): Long = x - y
    override fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long = when {
        x != null && y == null -> x - z
        x == null && y != null -> z + y
        else -> throw IllegalArgumentException("One value needs to be null and the other not")
    }
}

data object ProductOperation : RiddleOperation() {
    override fun execute(x: Long, y: Long): Long = x * y
    override fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long = when {
        x != null && y == null -> z / x
        x == null && y != null -> z / y
        else -> throw IllegalArgumentException("One value needs to be null and the other not")
    }
}

data object DivisionOperation : RiddleOperation() {
    override fun execute(x: Long, y: Long): Long = x / y
    override fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long = when {
        x != null && y == null -> x / z
        x == null && y != null -> z * y
        else -> throw IllegalArgumentException("One value needs to be null and the other not")
    }
}

data object NoOp : RiddleOperation() {
    override fun execute(x: Long, y: Long): Long = throw NotImplementedError("NOOP")
    override fun calculateMissingValue(x: Long?, y: Long?, z: Long): Long  = throw NotImplementedError("NOOP")
}
