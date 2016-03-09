var ImbLoader = function (require, exports, module) {
    
    var exec = require('cordova/exec');
    
    var ImbPrintPlugin = function () {
	
    };
    
    ImbPrintPlugin.prototype.printText = function (text, successCallback, errorCallback) {
        
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
            ["WOOSIM", text]
        );
    };
    
    module.exports = new ImbPrintPlugin();
};

ImbLoader(require, exports, module);
cordova.define("cordova/plugin/ImbPrintPlugin", ImbLoader);
