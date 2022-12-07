package me.sivieri.aoc2022.day7

data class Directory(
    val name: String,
    val parent: Directory?,
    val subdirectories: MutableList<Directory>,
    val files: MutableList<File>
) {

    fun size(): Int = subdirectories.sumOf { it.size() } + files.sumOf { it.size }

    fun getSubdirectoryByName(name: String): Directory? = subdirectories.find { it.name == name }

    fun getFileByName(name: String): File? = files.find { it.name == name }

    fun addSubdirIfNotExists(name: String): Directory {
        if (getSubdirectoryByName(name) == null) subdirectories.add(
            Directory(
                name,
                this,
                mutableListOf(),
                mutableListOf()
            )
        )
        return this
    }

    fun addFileIfNotExists(name: String, size: Int): Directory {
        if (getFileByName(name) == null) files.add(File(name, size))
        return this
    }

    fun getAllSubdirectories(): List<Directory> = subdirectories.flatMap { it.getAllSubdirectories() } + this

    fun countAllFiles(): Int = files.size + subdirectories.sumOf { it.countAllFiles() }

    fun countAllDirectories(): Int = 1 + subdirectories.sumOf { it.countAllDirectories() }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Directory

        if (name != other.name) return false
        if (subdirectories != other.subdirectories) return false
        if (files != other.files) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + subdirectories.hashCode()
        result = 31 * result + files.hashCode()
        return result
    }

    override fun toString(): String {
        val cur = "- $name (dir)"
        val files = files.map { "  $it" }
        val directories = subdirectories.map {
            it
                .toString()
                .split("\n")
                .joinToString("\n") { "  $it" }
        }
        return (listOf(cur) + directories + files).joinToString("\n")
    }

}
