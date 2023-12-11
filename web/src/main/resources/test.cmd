@ECHO OFF
ECHO Uncomment below command in the first time to install libraries:
ECHO pip install SpeechRecognition pydub
ECHO .

REM pip install SpeechRecognition pydub
ECHO Testing python script with audio "This audio program has been produced by ybm.All rights reserved."
python audio2text.py 1_Intro.mp3
@PAUSE