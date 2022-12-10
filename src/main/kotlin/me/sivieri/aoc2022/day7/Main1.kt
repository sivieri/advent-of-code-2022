package me.sivieri.aoc2022.day7

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(7)
        val fileSystemInterpreter = FileSystemInterpreter(data)
        println(fileSystemInterpreter)
        println(fileSystemInterpreter.root.countAllFiles())
        println(fileSystemInterpreter.root.countAllDirectories())
        val result = fileSystemInterpreter.findTotalSizeOfAtMost(100000)
        println(result)
    }

}