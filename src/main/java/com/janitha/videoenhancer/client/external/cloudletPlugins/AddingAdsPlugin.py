import sys
from streamingHost import StreamingHost 
from camera import VideoCamera
import cv2

ds_factor=0.6
class AddingAds(VideoCamera):
    
    list1 = ["Hello From Cloudlet", "This is an ads"]
    
    def __init__(self, url):
        super().__init__(url)

    
    def get_frame(self):
        ret, frame = self.video.read()
        font_face = cv2.FONT_HERSHEY_SIMPLEX
        scale = 2
        color = (0, 0, 0)
        thickness = cv2.FILLED
        margin = 2
        
        text = "Hello From Cloudlet"
        txt_size = cv2.getTextSize(text, font_face, scale, thickness)


        frame=cv2.resize(frame,None,fx=ds_factor,fy=ds_factor,
        interpolation=cv2.INTER_AREA)
        pos = (100,100)
        bg_color = (255,0,0)

        end_x = pos[0] + txt_size[0][0] + margin
        end_y = pos[1] - txt_size[0][1] - margin

        cv2.rectangle(frame, pos, (end_x, end_y), bg_color, thickness)
        cv2.putText(frame, text, pos, font_face, scale, color, 1, cv2.LINE_AA)
        ret, jpeg = cv2.imencode('.jpg', frame)
        return jpeg.tobytes()



n = len(sys.argv)
host = sys.argv[1]
port = sys.argv[2]
cloudletPort = sys.argv[3]
URL = sys.argv[4]

print("\n", host, " ", port, " ", URL)

originalCamera = AddingAds(URL)

StreamingHost(host, port, cloudletPort, originalCamera)


print('End Succefully')
