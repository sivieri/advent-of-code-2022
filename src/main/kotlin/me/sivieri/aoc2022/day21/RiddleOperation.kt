package me.sivieri.aoc2022.day21

sealed class RiddleOperation {
    abstract fun execute(value1: Long, value2: Long): Long

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
    override fun execute(value1: Long, value2: Long): Long = value1 + value2
}

data object SubtractionOperation : RiddleOperation() {
    override fun execute(value1: Long, value2: Long): Long = value1 - value2
}

data object ProductOperation : RiddleOperation() {
    override fun execute(value1: Long, value2: Long): Long = value1 * value2
}

data object DivisionOperation : RiddleOperation() {
    override fun execute(value1: Long, value2: Long): Long = value1 / value2
}

data object NoOp : RiddleOperation() {
    override fun execute(value1: Long, value2: Long): Long = throw NotImplementedError("NOOP")
}
