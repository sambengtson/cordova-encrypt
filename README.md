# cordova-encrypt
Encryption in javascript sucks.  Let's make it a little easier

As of right now, the only algorithm that is supported is SHA-256 on Android.  Eventually, I would like this to become a thing where we have multiple algorithms implemented for each operating system.

Installation
```
cordova plugin add https://github.com/sambengtson/cordova-encrypt.git
```

Usage
```
        CordovaEncrypt.encrypt('sha256', ['string-to-encrypt'], function(err, result){
            console.log(result);
        }, function(err, result){
            console.log(err);
        });
```
