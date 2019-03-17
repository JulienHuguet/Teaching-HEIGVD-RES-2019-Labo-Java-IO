package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    int beginIndex = off;
    int endIndex = beginIndex + len;
    String substrTMP = str.substring(beginIndex, endIndex);

    substrTMP = substrTMP.toUpperCase();

    super.out.write(substrTMP);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(String.valueOf(cbuf),off,len);
  }

  @Override
  public void write(int c) throws IOException {
    super.out.write(Character.toUpperCase(c));

  }

}