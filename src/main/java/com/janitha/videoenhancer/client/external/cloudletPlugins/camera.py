import cv2, pafy

ds_factor=0.6
class VideoCamera(object):
    url_ = ""
    
    def __init__(self, url):
        self.url_ = url
 
        video = pafy.new(self.url_)
        best  = video.getbest()
        best.resolution, best.extension
 
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