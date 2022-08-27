#camera.py# import the necessary packages
import cv2# defining face detector
import cv2, pafy

ds_factor=0.6
class VideoCamera(object):
    def __init__(self):
       url   = "https://www.youtube.com/watch?v=2fmkmp-_KH0"

       video = pafy.new(url)
       best  = video.getbest()
       best.resolution, best.extension
       #documentation: https://pypi.org/project/pafy/

       capture = cv2.VideoCapture(best.url)
       check, frame = capture.read()

       self.video = cv2.VideoCapture(best.url)
    
    def __del__(self):
        #releasing camera
        self.video.release()
    
    def get_frame(self):
       #extracting frames
        ret, frame = self.video.read()
        frame=cv2.resize(frame,None,fx=ds_factor,fy=ds_factor,
        interpolation=cv2.INTER_AREA)                    
        gray=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        ret, jpeg = cv2.imencode('.jpg', frame)
        return jpeg.tobytes()