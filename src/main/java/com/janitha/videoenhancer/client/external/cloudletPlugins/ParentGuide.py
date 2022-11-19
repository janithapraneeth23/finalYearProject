import sys
from streamingHost import StreamingHost 
from camera import VideoCamera
import cv2
import features
import timeit
import os

ds_factor=1
class ParentGuide(VideoCamera):  
    def __init__(self, url):
        path = os.getcwd()

        print(path)
        img = cv2.imread('logo_train.png')
        self.train_features = features.getFeatures(img)
        self.cur_time = timeit.default_timer()
        self.frame_number = 0
        self.scan_fps = 0
        super().__init__(url)

    
    def get_frame(self):
        ret, frame = self.video.read()
        frame=cv2.resize(frame,None,fx=ds_factor,fy=ds_factor,
        interpolation=cv2.INTER_AREA)
        height, width = frame.shape[:2]
        w, h = (16, 16)

        self.frame_number += 1
        if not self.frame_number % 100:
            self.scan_fps = 1 / ((timeit.default_timer() - self.cur_time) / 100)
            self.cur_time = timeit.default_timer()

        region = features.detectFeatures(frame, self.train_features)

        cv2.putText(frame, f'FPS {self.scan_fps:.3f}', org=(0, 50),
                    fontFace=cv2.FONT_HERSHEY_COMPLEX_SMALL,
                    fontScale=1, color=(0, 0, 255))

        if region is not None:
            temp = cv2.resize(frame, (w, h), interpolation=cv2.INTER_LINEAR)

# Initialize output image
            output = cv2.resize(temp, (width, height), interpolation=cv2.INTER_NEAREST)

            #id_kernel = np.array([[0, 0, 0], [0, 1, 0], [0, 0, 0]])
            #blur_img2 = cv2.GaussianBlur(frame, (45, 45), 1000)
            #blur_img = cv2.GaussianBlur(blur_img2, (45, 45), 1000)
            cv2.waitKey(0)
            ret, jpeg = cv2.imencode('.jpg', output)
            return jpeg.tobytes()
            #box = cv2.boxPoints(region)
            #box = np.int0(box)
            #cv2.drawContours(frame, [box], 0, (0, 255, 0), 2)


        #gray=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        cv2.waitKey(0)
        ret, jpeg = cv2.imencode('.jpg', frame)
        return jpeg.tobytes()
    
    def get_audio(self):
        #play_url = self.bestaudio.url
        return ""
    


n = len(sys.argv)
host = sys.argv[1]
port = sys.argv[2]
cloudletPort = sys.argv[3]
URL = sys.argv[4]


print("\n", host, " ", port, " ", URL)

originalCamera = ParentGuide(URL)



StreamingHost(host, port, cloudletPort, originalCamera)


print('End Succefully')
