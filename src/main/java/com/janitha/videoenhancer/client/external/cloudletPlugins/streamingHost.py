
from flask import Flask, render_template, Response,make_response
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
    print("Hellow22\n\n")
    return Response(gen(originalCamera),
                    mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/audio_feed')
def audio_feed():
    #print(originalCamera.get_audio())
    ##print("Hellow\n\n")
    #response = make_response(originalCamera.get_audio(), 200)
    #response.mimetype = "text/plain"
    return 	'<audio controls autoplay=true>  <source src=' + originalCamera.get_audio() + ' type="audio/ogg">element </audio>'

class StreamingHost(object):
    def __init__(self, hostInput, portInput, cameraInput):
        global originalCamera
        originalCamera = cameraInput
        print("\n", hostInput, " ", portInput)
        serve(app, host=hostInput, port=portInput) #, debug=False)
        #app.run(host=hostInput, port=portInput) #, debug=False