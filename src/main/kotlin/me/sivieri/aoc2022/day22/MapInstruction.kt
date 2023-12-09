package me.sivieri.aoc2022.day22

sealed class MapInstruction

data class MoveInstruction(val move: Int): MapInstruction()

data object RotateClockwiseInstruction : MapInstruction()

data object RotateCounterClockwiseInstruction : MapInstruction()
