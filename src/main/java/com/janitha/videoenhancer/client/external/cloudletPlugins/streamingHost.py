
from flask import Flask, render_template, Response,make_response
#from camera import VideoCamera
from waitress import serve
import json
import sys



app = Flask(__name__)
originalCamera = 0
originalHomePort = ""
originalPort = ""
originalIP = ""

@app.route('/')
def index():
    # rendering webpage
    return render_template('index.html')
    
def gen(camera):
    try:
        while True:
        #get camera frame
            frame = camera.get_frame()
            yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')
    except Exception as e:
        sys.exit()

        
@app.route('/video_feed')
def video_feed():
    print("Hellow22\n\n")
    return Response(gen(originalCamera),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

@app.route('/cludlet_status')
def cludlet_status():
    return "running"

@app.route('/cludlet_details')
def cludlet_details():
    Details = { "CloudletProcessIP" : "",
                "CloudletProcessPort" : "",
                "CloudletHomePort" : ""
               }
    Details["CloudletHomePort"] = originalHomePort
    Details["CloudletProcessPort"] = originalPort
    Details["CloudletProcessIP"] = originalIP

    fileJOut = json.dumps(Details)
    return fileJOut


@app.route('/audio_feed')
def audio_feed():
    #print(originalCamera.get_audio())
    ##print("Hellow\n\n")
    #response = make_response(originalCamera.get_audio(), 200)
    #response.mimetype = "text/plain"
    return 	'<audio controls autoplay=true>  <source src=' + originalCamera.get_audio() + ' type="audio/ogg">element </audio>'

class StreamingHost(object):
    def __init__(self, hostInput, portInput, homePort, cameraInput):
        global originalCamera
        originalCamera = cameraInput
        global originalHomePort
        originalHomePort = homePort
        global originalPort
        originalPort = portInput
        global originalIP
        originalIP = hostInput

        print("\n", hostInput, " ", portInput)
        serve(app, host=hostInput, port=portInput) #, debug=False)
        #app.run(host=hostInput, port=portInput) #, debug=False