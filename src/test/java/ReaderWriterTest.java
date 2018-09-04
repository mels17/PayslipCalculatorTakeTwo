package test.java;

import main.kotlin.ConsoleReader;
import main.kotlin.ConsoleWriter;
import main.kotlin.MyReader;
import main.kotlin.MyWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ReaderWriterTest {
    MyWriter writer = new ConsoleWriter();
    MyReader reader = new ConsoleReader();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("Test String".getBytes());
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testConsoleOutput() {
        String testString = "Testing console output";
        writer.write(testString);
        assertEquals(testString, outContent.toString());
    }

    @Test
    public void testConsoleInput() {
        assertEquals("Test String", reader.read());
    }

}
