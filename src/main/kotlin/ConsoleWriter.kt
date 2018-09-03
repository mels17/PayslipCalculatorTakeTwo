package main.kotlin

class ConsoleWriter: MyWriter {
    override fun write(message: String) = print(message)
}