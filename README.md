# painting

fbset mode
mode "1440x900"


Extraction of image command
ffmpeg -i ChildScare2.mp4 -vf "select=eq(n\,0)" -vf scale=-2:900 -q:v 3 -frames:v 1 output_image2.jpg


install libs
* ffmpeg
* omxplayer
* git
* openjdk-8-jdk
