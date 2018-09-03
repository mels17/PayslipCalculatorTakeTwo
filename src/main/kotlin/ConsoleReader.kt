package main.kotlin

class ConsoleReader: MyReader {
    override fun read(): String {
        return readLine()!!
    }
}