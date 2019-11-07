/*global cordova, module*/
var exec = require('cordova/exec');

var ffmpeg = {
    exec: function (fileName,fileType, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "FFMpeg", "exec", [fileName,fileType]);
    }
};
module.exports = ffmpeg;