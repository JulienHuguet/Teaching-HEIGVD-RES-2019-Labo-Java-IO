package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int numberLines;
  private char endChar;
  private boolean firstLine;

  public FileNumberingFilterWriter(Writer out) {

    super(out);
    numberLines = 0;
    firstLine = true;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < len + off; ++i){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    char input = (char)c;

    if(firstLine){
      out.write(++numberLines + "\t");
      firstLine = false;
    }
    if(endChar == '\r'){
      ++numberLines;
      if(input == '\n'){
        out.write("\n" + numberLines + "\t");
        endChar = input;
      }
    }else if(input == '\n'){
      ++numberLines;
      out.write("\n" + numberLines + "\t");
      endChar = input;
    }else{
      out.write(input);
      endChar = input;
    }

  }

}
