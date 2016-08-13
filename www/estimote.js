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

/*
 * Register callbacks for packagepay log messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.onLog = function (success, error) {
    cordova.exec(success, error, feature, "onLog", []);
};

module.exports = self;
