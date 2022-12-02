package me.sivieri.aoc2022.day2

class Strategy {

    fun resolve(
        strategy: List<Pair<Char, Char>>,
        response: Map<Char, RPSMove>
    ): Int =
        strategy.sumOf { (yourChoice, theirChoice) ->
            calculateScorePoints(
                response[theirChoice]!!,
                RPSMove.fromLetter(yourChoice)
            )
        }

    companion object {
        private const val WIN = 6
        private const val DRAW = 3
        private const val LOSE = 0

        private fun calculateScorePoints(yourChoice: RPSMove, theirChoice: RPSMove): Int =
            yourChoice.shapePoints + when (yourChoice) {
                RPSMove.Rock -> {
                    when (theirChoice) {
                        RPSMove.Rock -> DRAW
                        RPSMove.Paper -> LOSE
                        RPSMove.Scissors -> WIN
                    }
                }
                RPSMove.Paper -> when (theirChoice) {
                    RPSMove.Rock -> WIN
                    RPSMove.Paper -> DRAW
                    RPSMove.Scissors -> LOSE
                }
                RPSMove.Scissors -> when (theirChoice) {
                    RPSMove.Rock -> LOSE
                    RPSMove.Paper -> WIN
                    RPSMove.Scissors -> DRAW
                }
            }

    }

}