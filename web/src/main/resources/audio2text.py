# came along this code in below:
# https://www.thepythoncode.com/article/using-speech-recognition-to-convert-speech-to-text-python
# modified it and made it more usable
# pip install SpeechRecognition pydub
# On Linux: sudo apt install ffmpeg



import speech_recognition as sr
import sys
import os
from pydub import AudioSegment
from pydub.silence import split_on_silence
import shutil

from datetime import datetime

# create a speech recognition object
r = sr.Recognizer()

def mp3_wav(path):
    # source file
    source = path

    # create a directory to store the converted audio file
    folder_name = "dummy"
    if not os.path.isdir(folder_name):
        os.mkdir(folder_name)
    temp_file = os.path.join(folder_name, "temp{:-%Y%m%d%H%M%S}.wav".format(datetime.now()))

    # convert wav to mp3
    sound = AudioSegment.from_mp3(source)
    sound.export(temp_file, format="wav")

    return temp_file

def extract_text(path):
    """
    splitting the large audio file into chunks
    and apply speech recognition on each of these chunks
    then delete the chunks and only return the text
    """

    file_name = mp3_wav(path)
    # open the audio file using pydub
    sound = AudioSegment.from_wav(file_name)

    # split audio sound where silence is 200 miliseconds or more and get chunks
    chunks = split_on_silence(sound,
                              # experiment with this value for your target audio file
                              min_silence_len=200,
                              # adjust this per requirement
                              silence_thresh=sound.dBFS - 14,
                              # keep the silence for 1 second, adjustable as well
                              keep_silence=300,
                              )

    # create a directory to store the audio chunks
    folder_name = "dummy"
    if not os.path.isdir(folder_name):
        os.mkdir(folder_name)

    whole_text = ""

    # process each chunk
    for i, audio_chunk in enumerate(chunks, start=1):
        # export audio chunk and save it in
        # the `folder_name` directory.
        chunk_filename = os.path.join(folder_name, f"chunk{i}.wav")
        audio_chunk.export(chunk_filename, format="wav")
        # recognize the chunk
        with sr.AudioFile(chunk_filename) as source:
            audio_listened = r.record(source)
            # try converting it to text
            try:
                text = r.recognize_google(audio_listened)
            except sr.UnknownValueError:
                continue
            else:
                text = f"{text.capitalize()}."
                whole_text += text

    # deleting the temp folder
    shutil.rmtree("dummy")

    # return the text for all chunks detected
    return whole_text

path = sys.argv[1]
whole_text = extract_text(path)
print(whole_text)
