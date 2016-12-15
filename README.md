# dff-cordova-plugin-estimote
cordova estimote plugin

Please see the following links for basic information:

 * https://github.com/Estimote/Android-SDK
 * http://developer.estimote.com/android/tutorial/part-1-setting-up/
 * http://estimote.github.io/Android-SDK/JavaDocs/

## Supportted platforms

 * Android

## Installation

```sh
$ cordova plugin add https://github.com/dff-solutions/dff-cordova-plugin-estimote.git
```

## Usage
Plugin is available via global `Estimote`.

### Actions


#### connect
```js
/**
 * Connect to estimote beacon manager.
 *
 * @name connect
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .connect(function () {
        console.log('connected');
    }, function (reason) {
        console.error(reason);
    });
```

#### disconnect

```js
/**
 * Disconnect from estimote beacon manager.
 * @name disconnect
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .disconnect(function () {
        console.log('disconnected');
    }, function (reason) {
        console.error(reason);
    });
```


#### onScanStart
```js
/**
 * Listen to scan start events.
 *
 * @name onScanStart
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onScanStart(function () {
        console.log('scan started');
    }, function (reason) {
        console.error(reason);
    });
```


#### onScanStop
```js
/**
 * Listen to scan stop events.
 *
 * @name onScanStop
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onScanStop(function () {
        console.log('scan stoped');
    }, function (reason) {
        console.error(reason);
    });
```


#### onEnteredRegion
```js
/**
 * Listen to entered regions.
 *
 * @name onEnteredRegion
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onEnteredRegion(function (region) {
        console.log(region);
    }, function (reason) {
        console.error(reason);
    });
```


#### onExitedRegion
```js
/**
 * Listen to exited regions.
 *
 * @name onExitedRegion
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onExitedRegion(function (region) {
        console.log(region);
    }, function (reason) {
        console.error(reason);
    });
```


#### onNearablesDiscovered
```js
/**
 * Listen to discovered nearabels.
 *
 * @name onNearablesDiscovered
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onNearablesDiscovered(function (nearables) {
        console.log(nearables);
    }, function (reason) {
        console.error(reason);
    });
```


#### onBeaconsDiscovered
```js
/**
 * Listen to discovered beacons.
 *
 * @name onBeaconsDiscovered
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onBeaconsDiscovered(function (region) {
        console.log(region);
    }, function (reason) {
        console.error(reason);
    });
```


#### onError
```js
/**
 * Listen to error with beacons.
 *
 * @name onError
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .onError(function (error) {
        console.error(error);
    }, function (reason) {
        console.error(reason);
    });
```

#### startNearableDiscovery

```js
/**
 * Start nearable discovery.
 * Resolves with scan identifier which is required to stop scanning
 *
 * @name startNearableDiscovery
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 */
Estimote
    .startNearableDiscovery(function (scanId) {
        console.log(scanId);
    }, function (reason) {
        console.error(reason);
    });
```

#### stopNearableDiscovery

```js
/**
 * @name stopNearableDiscovery
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.scanId Scan identifier for stop scanning.
 */
Estimote
    .stopNearableDiscovery(function () {
        console.log('stoppped nearable discovery');
    }, function (reason) {
        console.error(reason);
    }, {
        scanId: 'foobar'
    });
```

#### startMonitoring

```js
/**
 * Starts beacon monitoring. You can monitor multiple regions at the same time.
 * Listener will be called for each region separately.
 *
 * @name startMonitoring
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.identifier A unique identifier for a region. Cannot be null.
 * @param {function} args.uuid Location UUID of beacons. Can be null. Null indicates all location UUIDs.
 * @param {function} args.major Major version of the beacons. Can be null. Null indicates all major versions.
 * @param {function} args.minor Minor version of the beacons. Can be null. Null indicates all minor versions.
 */
Estimote
    .startMonitoring(function () {
        console.log('started monitoring');
    }, function (reason) {
        console.error(reason);
    }, {
        identifier: 'dff solutions'
        major: 12
        minor: 1
        uuid: 'B9407F30-F5F8-466E-AFF9-25556B57FE6D'
    });
```


#### stopMonitoring
```js
/**
 * Stops region monitoring that was previously started with startMonitoring.
 *
 * @name stopMonitoring
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.identifier A unique identifier for a region. Cannot be null.
 * @param {function} args.uuid Location UUID of beacons. Can be null. Null indicates all location UUIDs.
 * @param {function} args.major Major version of the beacons. Can be null. Null indicates all major versions.
 * @param {function} args.minor Minor version of the beacons. Can be null. Null indicates all minor versions.
 */
Estimote
    .stopMonitoring(function () {

    }, function (reason) {
        console.error(reason);
    }, {
        identifier: 'dff solutions'
        major: 12
        minor: 1
        uuid: 'B9407F30-F5F8-466E-AFF9-25556B57FE6D'
    });
```

#### startRanging

```js
/**
 * Starts ranging given range.
 * Ranging results will be delivered to listener registered via setRangingListener(RangingListener).
 * If given region is already ranged, this is no-op.
 * You can range multiple regions at the same time.
 * Listener will be called for each region separately.
 *
 * @name startRanging
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.identifier A unique identifier for a region. Cannot be null.
 * @param {function} args.uuid Location UUID of beacons. Can be null. Null indicates all location UUIDs.
 * @param {function} args.major Major version of the beacons. Can be null. Null indicates all major versions.
 * @param {function} args.minor Minor version of the beacons. Can be null. Null indicates all minor versions.
 */
Estimote
    .startRanging(function () {
        console.log('started ranging');
    }, function (reason) {
        console.error(reason);
    }, {
        identifier: 'dff solutions'
        major: 12
        minor: 1
        uuid: 'B9407F30-F5F8-466E-AFF9-25556B57FE6D'
    });
```


#### stopRanging
```js
/**
 * Stops ranging for beacons that was starting with startRanging method.
 *
 * @name stopRanging
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.identifier A unique identifier for a region. Cannot be null.
 * @param {function} args.uuid Location UUID of beacons. Can be null. Null indicates all location UUIDs.
 * @param {function} args.major Major version of the beacons. Can be null. Null indicates all major versions.
 * @param {function} args.minor Minor version of the beacons. Can be null. Null indicates all minor versions.
 */
Estimote
    .stopRanging(function () {
        console.log('stoped ranging');
    }, function (reason) {
        console.error(reason);
    }, {
        identifier: 'dff solutions'
        major: 12
        minor: 1
        uuid: 'B9407F30-F5F8-466E-AFF9-25556B57FE6D'
    });
```

#### setBackgroundScanPeriod

```js
/**
 * Changes defaults scanning periods when only monitoring is performed.
 * @name setBackgroundScanPeriod
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.scanPeriodMillis How long to perform Bluetooth Low Energy scanning?
 * @param {function} args.waitTimeMillis How long to wait until performing next scanning?
 */
Estimote
    .setBackgroundScanPeriod(function () {
    }, function (reason) {
        console.error(reason);
    }, {
        scanPeriodMillis: 5000,
        waitTimeMillis: 25000
    });
```


#### setForegroundScanPeriod
```js
/**
 * Changes defaults scanning periods when ranging is performed.
 *
 * @name setForegroundScanPeriod
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {function} args Named arguments.
 * @param {function} args.scanPeriodMillis How long to perform Bluetooth Low Energy scanning?
 * @param {function} args.waitTimeMillis How long to wait until performing next scanning?
 */
Estimote
    .setForegroundScanPeriod(function () {
    }, function (reason) {
        console.error(reason);
    }, {
        scanPeriodMillis: 1000,
        waitTimeMillis: 0
    });
```
