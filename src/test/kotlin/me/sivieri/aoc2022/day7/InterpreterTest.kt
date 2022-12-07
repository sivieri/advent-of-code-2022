package me.sivieri.aoc2022.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class InterpreterTest {

    @Test
    fun `part 1 example tree generation`() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().split("\n").map { it.trim() }
        val interpreter = Interpreter(input)
        val root = Directory("/", null, mutableListOf(), mutableListOf(
            File("b.txt", 14848514),
            File("c.dat", 8504156)
        ))
        val a = Directory("a", root, mutableListOf(), mutableListOf(
            File("f", 29116),
            File("g", 2557),
            File("h.lst", 62596)
        ))
        val d = Directory("d", root, mutableListOf(), mutableListOf(
            File("j", 4060174),
            File("d.log", 8033020),
            File("d.ext", 5626152),
            File("k", 7214296)
        ))
        root.subdirectories.add(a)
        root.subdirectories.add(d)
        val e = Directory("e", a, mutableListOf(), mutableListOf(
            File("i", 584)
        ))
        a.subdirectories.add(e)
        println(interpreter.root)
        assertThat(interpreter.root, `is`(root))
    }

    @Test
    fun `part 1 example total size at most 100000`() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().split("\n").map { it.trim() }
        val interpreter = Interpreter(input)
        val root = Directory("/", null, mutableListOf(), mutableListOf(
            File("b.txt", 14848514),
            File("c.dat", 8504156)
        ))
        val a = Directory("a", root, mutableListOf(), mutableListOf(
            File("f", 29116),
            File("g", 2557),
            File("h.lst", 62596)
        ))
        val d = Directory("d", root, mutableListOf(), mutableListOf(
            File("j", 4060174),
            File("d.log", 8033020),
            File("d.ext", 5626152),
            File("k", 7214296)
        ))
        root.subdirectories.add(a)
        root.subdirectories.add(d)
        val e = Directory("e", a, mutableListOf(), mutableListOf(
            File("i", 584)
        ))
        a.subdirectories.add(e)
        assertThat(interpreter.findTotalSizeOfAtMost(100000), `is`(95437))
    }

}