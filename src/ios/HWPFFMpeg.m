#import "HWPFFMpeg.h"
#import <mobileffmpeg/MobileFFmpeg.h>

@implementation HWPFFMpeg

- (void)exec:(CDVInvokedUrlCommand*)command {
    //https://github.com/tanersener/mobile-ffmpeg/wiki/IOS
    NSString* fileName = [[command arguments] objectAtIndex:0];
    NSString* fileType = [[command arguments] objectAtIndex:1];
    NSString* responseToUser;

    // 取得沙盒目录
    NSString *localPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
     
    NSString *sourceFileName = [NSString stringWithFormat:@"%@%@",fileName,@"."+fileType];
    NSString *toFileName = [NSString stringWithFormat:@"%@%@",fileName+@"_ffmpeg",@".mp3"];
    
    NSString *sourceUrl = [localPath  stringByAppendingPathComponent:sourceFileName];
    NSString *targetUrl = [localPath  stringByAppendingPathComponent:toFileName];

    [MobileFFmpeg execute: @"-i "+sourceUrl+" "+targetUrl];

    int returnCode = [MobileFFmpeg getLastReturnCode];
    NSString *output = [MobileFFmpeg getLastCommandOutput];

    if (returnCode == RETURN_CODE_SUCCESS) {
        responseToUser = [NSString stringWithFormat: @"success out=%@", output];

    } else if (returnCode == RETURN_CODE_CANCEL) {
        responseToUser = [NSString stringWithFormat: @"canceld"];

    } else {
        responseToUser = [NSString stringWithFormat: @"failure code=%d out=%@", returnCode, output];

    }


    CDVPluginResult* result = [CDVPluginResult
                               resultWithStatus:CDVCommandStatus_OK
                               messageAsString:responseToUser];

    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

@end
