# painting 

***
Just a small bot for integrating of small linux commands with telegram.<br>
Bot is created in Spring Boot and should be started with 3 environment variables

* telegram.bot.username
* telegram.bot.token
* spring.profiles.active

There are 2 profiles defined. 
<table>
    <tr>
        <td>win</td>
        <td>this profile will mock most commands. It assumes that you have a home folder at c:\home</td>
    </tr>
    <tr>
        <td>pi</td>
        <td>this profile will execute all the commands.</td>
    </tr>
</table>

Be aware that for each type in the FileType enum you need to have a subfolder in your home folder.<br/>
For now this is only JPEG and 
 
***

fbset mode
mode "1440x900"


Extraction of image command
ffmpeg -i ChildScare2.mp4 -vf "select=eq(n\,0)" -vf scale=-2:900 -q:v 3 -frames:v 1 output_image2.jpg


install libs
* ffmpeg
* omxplayer
* git
* openjdk-8-jdk
* imagemagick 
* screen