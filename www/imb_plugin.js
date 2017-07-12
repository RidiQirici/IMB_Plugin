﻿var ImbLoader = function (require, exports, module) {
    
    var exec = require('cordova/exec');
    
    var ImbPrintPlugin = function () {
	
    };
    
    ImbPrintPlugin.prototype.printText = function (pajisja, text, successCallback, errorCallback) {
        
        if (successCallback === null) {
            successCallback = function (response) {
                console.log('Plugin.printText sukses: ' + response);
            };
        }
        
        if (errorCallback === null) {
            errorCallback = function (error) {
                console.error('Plugin.printText deshtim: ' + error);
            };
        }
        
        if (typeof errorCallback != "function") {
            console.error("Plugin.printText failure: parametri deshtimit nuk eshte funksion");
            return;
        }
        
        if (typeof successCallback != "function") {
            console.error("Plugin.printText failure: parametri callback i suksesit duhet te jete patjeter funksion");
            return;
        }
        
        exec(
            successCallback,
            errorCallback,
            "Plugin",
            "printText",
            [pajisja, text]
        );
    };
	
	ImbPrintPlugin.prototype.printTextMeMac = function (pajisja, text, adresa, successCallback, errorCallback) {
        
        if (successCallback === null) {
            successCallback = function (response) {
                console.log('Plugin.printText sukses: ' + response);
            };
        }
        
        if (errorCallback === null) {
            errorCallback = function (error) {
                console.error('Plugin.printText deshtim: ' + error);
            };
        }
        
        if (typeof errorCallback != "function") {
            console.error("Plugin.printText failure: parametri deshtimit nuk eshte funksion");
            return;
        }
        
        if (typeof successCallback != "function") {
            console.error("Plugin.printText failure: parametri callback i suksesit duhet te jete patjeter funksion");
            return;
        }
        
        exec(
            successCallback,
            errorCallback,
            "Plugin",
            "printTextMeMac",
            [pajisja, text, adresa]
        );
    };
    
    ImbPrintPlugin.prototype.printTextMePermasa = function (pajisja, text, wight, hight, successCallback, errorCallback) {
        
        if (successCallback === null) {
            successCallback = function (response) {
                console.log('Plugin.printText sukses: ' + response);
            };
        }
        
        if (errorCallback === null) {
            errorCallback = function (error) {
                console.error('Plugin.printText deshtim: ' + error);
            };
        }
        
        if (typeof errorCallback != "function") {
            console.error("Plugin.printText failure: parametri deshtimit nuk eshte funksion");
            return;
        }
        
        if (typeof successCallback != "function") {
            console.error("Plugin.printText failure: parametri callback i suksesit duhet te jete patjeter funksion");
            return;
        }
        
        exec(
            successCallback,
            errorCallback,
            "Plugin",
            "printTextMePermasa",
            [pajisja, text, wight, hight]
        );
    };

    ImbPrintPlugin.prototype.printBarcode = function (pajisja, text, successCallback, errorCallback) {
        
        if (successCallback === null) {
            successCallback = function (response) {
                console.log('Plugin.printBarcode sukses: ' + response);
            };
        }
        
        if (errorCallback === null) {
            errorCallback = function (error) {
                console.error('Plugin.printBarcode deshtim: ' + error);
            };
        }
        
        if (typeof errorCallback != "function") {
            console.error("Plugin.printBarcode failure: parametri deshtimit nuk eshte funksion");
            return;
        }
        
        if (typeof successCallback != "function") {
            console.error("Plugin.printBarcode failure: parametri callback i suksesit duhet te jete patjeter funksion");
            return;
        }
        
        exec(
            successCallback,
            errorCallback,
            "Plugin",
            "printBarcode",
            [pajisja, text]
        );
    };

    module.exports = new ImbPrintPlugin();
};

ImbLoader(require, exports, module);
cordova.define("cordova/plugin/ImbPrintPlugin", ImbLoader);
