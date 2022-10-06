
from flask import Flask, render_template, Response
#from camera import VideoCamera
from waitress import serve



app = Flask(__name__)
originalCamera = 0

@app.route('/')
def index():
    # rendering webpage
    return render_template('index.html')
    
def gen(camera):
    while True:
        #get camera frame
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')
        
@app.route('/video_feed')
def video_feed():
    return Response(gen(originalCamera),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

class StreamingHost(object):
    def __init__(self, hostInput, portInput, URLInput, cameraInput):
        global originalCamera
        originalCamera = cameraInput
        
        serve(app, host=hostInput, port=portInput) #, debug=False)