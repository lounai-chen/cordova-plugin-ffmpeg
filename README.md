# cordova插件，使用ffmpeg转码MP3

需要配合以下插件一起使用

cordova-plugin-media （录音）

cordova-plugin-file （文件路径 ）


参考

https://github.com/tanersener/mobile-ffmpeg/wiki/Android

https://github.com/adminy/cordova-plugin-ffmpeg

# Cordova FFMPEG (mp3) Plugin

Simple plugin that binds mobile ffmpeg to execute ffmpeg commands

## Using

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello
    
Install the plugin

    $ cd hello
    $ cordova plugin add https://github.com/lounai-chen/cordova-plugin-ffmpeg
    

Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
    ffmpeg.exec("-i someinput.wav out.mp3", success => alert(success), failure => alert(failure));
```

Make sure you have the files that will be required by ffmpeg

Install iOS or Android platform

    cordova platform add ios
    cordova platform add android
    
Run the code

    cordova run
