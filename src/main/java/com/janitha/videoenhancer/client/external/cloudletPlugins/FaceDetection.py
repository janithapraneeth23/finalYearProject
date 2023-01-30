import sys
from streamingHost import StreamingHost 
from camera import VideoCamera
import cv2
import features
import timeit
import os

face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

ds_factor=1
class ParentGuide(VideoCamera):  
    def __init__(self, url):
        path = os.getcwd()

        print(path)
        img = cv2.imread('C:\\Personal\\Msc\\Project\\server\\server\\src\\main\\java\\com\\janitha\\videoenhancer\\client\\external\\cloudletPlugins\\logo_train.png')
        self.train_features = features.getFeatures(img)
        self.cur_time = timeit.default_timer()
        self.frame_number = 0
        self.scan_fps = 0
        super().__init__(url)

    
    def get_frame(self):
        ret, frame = self.video.read()
        frame=cv2.resize(frame,None,fx=ds_factor,fy=ds_factor,
        interpolation=cv2.INTER_AREA)  
        gray=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(
            gray,
            scaleFactor=1.1,
            minNeighbors=5,
            minSize=(30, 30),
            flags = cv2.CASCADE_SCALE_IMAGE
        )
        for (x, y, w, h) in faces:
            cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
        
        cv2.waitKey(0)
        ret, jpeg = cv2.imencode('.jpg', frame)
        return jpeg.tobytes()
    
    def get_audio(self):
        play_url = self.bestaudio.url
        return play_url
    


n = len(sys.argv)
host = sys.argv[1]
port = sys.argv[2]
cloudletPort = sys.argv[3]
URL = sys.argv[4]


print("\n", host, " ", port, " ", URL)

originalCamera = ParentGuide(URL)



StreamingHost(host, port, cloudletPort, originalCamera)


print('End Succefully')
