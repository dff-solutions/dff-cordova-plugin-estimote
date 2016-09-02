/**
 * JavaScript interface to abstract
 * the usage of the cordova Sygic Navigation plugin.
 *
 * @module com/dff/cordova/plugins/sygic
 */

'use strict';

var cordova = require('cordova');
var feature = "EstimotePlugin";
var self = {};

var actions = [
    "onLog",
    "connect",
    "disconnect",
    "onScanStart",
    "onScanStop",
    "onEnteredRegion",
    "onExitedRegion",
    "onNearablesDiscovered",
    "onBeaconsDiscovered",
    "onError",
    "startNearableDiscovery",
    "stopNearableDiscovery",
    "startMonitoring",
    "stopMonitoring",
    "startRanging",
    "stopRanging",
    "setBackgroundScanPeriod",
    "setForegroundScanPeriod"
];

function createActionFunction(action) {
    return function (success, error, args) {
        cordova.exec(success, error, feature, action, [args]);
    }
}

actions.forEach(function (action) {
    self[action] = createActionFunction(action);
});

module.exports = self;
