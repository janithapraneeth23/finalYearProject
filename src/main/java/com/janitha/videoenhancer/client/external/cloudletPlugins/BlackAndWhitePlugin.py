import sys
from streamingHost import StreamingHost 
from camera import VideoCamera
import cv2

ds_factor=0.6
class BlackAndWhite(VideoCamera):  
    def __init__(self, url):
        super().__init__(url)

    
    def get_frame(self):
        ret, frame = self.video.read()
        frame=cv2.resize(frame,None,fx=ds_factor,fy=ds_factor,
        interpolation=cv2.INTER_AREA)                    
        gray=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        ret, jpeg = cv2.imencode('.jpg', gray)
        return jpeg.tobytes()



n = len(sys.argv)
host = sys.argv[1]
port = sys.argv[2]
URL = sys.argv[3]

print("\n", host, " ", port, " ", URL)

originalCamera = BlackAndWhite(URL)



StreamingHost(host, port, originalCamera)


print('End Succefully')
