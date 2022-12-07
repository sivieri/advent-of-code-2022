package me.sivieri.aoc2022.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import kotlin.test.assertNull

class DirectoryTest {

    @Test
    fun `size when empty`() {
        val directory = Directory("test", null, mutableListOf(), mutableListOf())
        assertThat(directory.size(), `is`(0))
    }

    @Test
    fun `size with files only`() {
        val directory = Directory(
            "test",
            null,
            mutableListOf(),
            mutableListOf(File("a", 1), File("b", 2), File("c", 3))
        )
        assertThat(directory.size(), `is`(6))
    }

    @Test
    fun `size with directories only`() {
        val directory = Directory(
            "test",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val sub1 = Directory(
            "sub1",
            directory,
            mutableListOf(),
            mutableListOf(File("a", 1), File("b", 2), File("c", 3))
        )
        val sub2 = Directory(
            "sub2",
            directory,
            mutableListOf(),
            mutableListOf(File("d", 4), File("e", 5), File("f", 6))
        )
        directory.subdirectories.add(sub1)
        directory.subdirectories.add(sub2)
        assertThat(directory.size(), `is`(21))
    }

    @Test
    fun `size with everything`() {
        val directory = Directory(
            "test",
            null,
            mutableListOf(),
            mutableListOf(File("g", 7), File("h", 8), File("i", 9))
        )
        val sub1 = Directory(
            "sub1",
            directory,
            mutableListOf(),
            mutableListOf(File("a", 1), File("b", 2), File("c", 3))
        )
        val sub2 = Directory(
            "sub2",
            directory,
            mutableListOf(),
            mutableListOf(File("d", 4), File("e", 5), File("f", 6))
        )
        directory.subdirectories.add(sub1)
        directory.subdirectories.add(sub2)
        assertThat(directory.size(), `is`(45))
    }

    @Test
    fun `find non-existing directory`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        assertNull(root.getSubdirectoryByName("three"))
    }

    @Test
    fun `find directory at first level`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        assertThat(root.getSubdirectoryByName("one")?.name, `is`("one"))
    }

    @Test
    fun `find directory in a subdirectory`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        val oneone = Directory("oneone", one, mutableListOf(), mutableListOf())
        val onetwo = Directory("onetwo", one, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        one.subdirectories.add(oneone)
        one.subdirectories.add(onetwo)
        assertNull(root.getSubdirectoryByName("onetwo")?.name)
    }

    @Test
    fun `find non-existing file`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        assertNull(root.getFileByName("one")?.name)
    }

    @Test
    fun `find file at first level`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf(
                File("one", 1),
                File("two", 2)
            )
        )
        assertThat(root.getFileByName("one")?.name, `is`("one"))
    }

    @Test
    fun `find file in a subdirectory`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf(
                File("one", 1),
                File("two", 2)
            )
        )
        val sub = Directory("sub", root, mutableListOf(), mutableListOf(
            File("subone", 3),
            File("subtwo", 4)
        ))
        root.subdirectories.add(sub)
        assertNull(root.getFileByName("subtwo")?.name)
    }

    @Test
    fun `add subdir that does not exist`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        root.addSubdirIfNotExists("three")
        assertThat(root.subdirectories.map { it.name }.contains("three"), `is`(true))
    }

    @Test
    fun `add subdir that does exist`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        root.addSubdirIfNotExists("one")
        assertThat(root.subdirectories.map { it.name }.contains("one"), `is`(true))
    }

    @Test
    fun `add file that does not exist`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf(
                File("one", 1),
                File("two", 2)
            )
        )
        root.addFileIfNotExists("three", 3)
        assertThat(root.files.contains(File("three", 3)), `is`(true))
    }

    @Test
    fun `add file that does exist`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf(
                File("one", 1),
                File("two", 2)
            )
        )
        root.addFileIfNotExists("one", 1)
        assertThat(root.files.contains(File("one", 1)), `is`(true))
    }

    @Test
    fun `get all subdirectories`() {
        val root = Directory(
            "root",
            null,
            mutableListOf(),
            mutableListOf()
        )
        val one = Directory("one", root, mutableListOf(), mutableListOf())
        val two = Directory("two", root, mutableListOf(), mutableListOf())
        val oneone = Directory("oneone", one, mutableListOf(), mutableListOf())
        val onetwo = Directory("onetwo", one, mutableListOf(), mutableListOf())
        root.subdirectories.add(one)
        root.subdirectories.add(two)
        one.subdirectories.add(oneone)
        one.subdirectories.add(onetwo)
        val subdirectories = root.getAllSubdirectories().map { it.name }
        assertThat(subdirectories, containsInAnyOrder("root", "one", "two", "oneone", "onetwo"))
    }

}