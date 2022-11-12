import pafy
import vlc
import speech_recognition as sr
import pyttsx3

r = sr.Recognizer()

def SpeakText(command):
     
    # Initialize the engine
    engine = pyttsx3.init()
    engine.say(command)
    engine.runAndWait()

video = pafy.new("https://youtu.be/tFfGu95mPO4")
best = video.getbestaudio()
play_url = best.url

print(play_url)

instance = vlc.Instance()
player = instance.media_player_new()
media = instance.media_new(play_url)
media.get_mrl()

while(1):
    
    with sr.AudioFile.AudioFileStream(play_url,1,1) as source:
        
        r.adjust_for_ambient_noise(source, duration=0.2)
        audio2 = r.listen(source)
        
        MyText = r.recognize_google(audio2)
        MyText = MyText.lower()
     
        print("Did you say ",MyText)
        SpeakText(MyText)
    

# instance = vlc.Instance()
# player = instance.media_player_new()
# media = instance.media_new(play_url)
# media.get_mrl()
# player.set_media(media)
# player.play()