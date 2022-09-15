import sys
from streamingHost import StreamingHost 

n = len(sys.argv)
port = sys.argv[1]
URL = sys.argv[2]

StreamingHost()

print("\n", port, " ", URL)

print('Hello, world!')
