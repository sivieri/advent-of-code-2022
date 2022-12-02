package me.sivieri.aoc2022.day2

class Strategy {

    fun resolveFromResponse(
        strategy: List<Pair<Char, Char>>,
        response: Map<Char, RPSMove>
    ): Int = strategy.sumOf { (theirChoice, yourChoice) ->
        val theirMove = RPSMove.fromLetter(theirChoice)
        val yourMove = response[yourChoice]!!
        yourMove.shapePoints + yourMove.resultAgainstMove(theirMove).points
    }

    fun resolveFromResult(
        strategy: List<Pair<Char, Char>>
    ): Int = strategy.sumOf { (theirChoice, result)  ->
        val theirMove = RPSMove.fromLetter(theirChoice)
        val requiredResult = Result.fromLetter(result)
        val yourMove = requiredResult.otherMove(theirMove)
        yourMove.shapePoints + yourMove.resultAgainstMove(theirMove).points
    }

}