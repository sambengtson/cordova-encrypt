var exec = require('cordova/exec');

function CordovaEncrypt() {
    console.log("CordovaEncrypt.js: is created");
}

CordovaEncrypt.prototype.encrypt = function (val, params,  callback) {

    exec(function (result) {
        if (callback) {
            callback(undefined, result);
        }
    },
        function (result) {

            if (callback) {
                callback(result, undefined);
            }

        }, "CordovaEncrypt", val, params);
}

var cordovaEncrypt = new CordovaEncrypt();
module.exports = cordovaEncrypt;

