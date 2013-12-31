//'use strict';
//
//(function () {
//    var TOPIC_NAME_ALERTS = 'alerts';
//    var helenApp = angular.module('helen', ['ui.bootstrap']);
//
//    helenApp
//        .controller('LoginCtrl', function ($scope, $location) {
//                        var url = "" + $location.$$absUrl;
//                        $scope.displayLoginError = (url.indexOf("error") >= 0);
//                        $scope.changeErrorVariableValue = function () {
//                            $scope.displayLoginError = !$scope.displayLoginError;
//                        }
//                    })
//    ;
//
//    helenApp.factory("PubSub", function () {
//        var cache, publish, subscribe, unsubscribe;
//        cache = {};
//        subscribe = function (topic, callback) {
//            if (!cache[topic]) {
//                cache[topic] = [];
//            }
//            cache[topic].push(callback);
//            return [topic, callback];
//        };
//        unsubscribe = function (topic, callback) {
//            cache[topic] && _.forEachRight(cache[topic], function (value, idx, collection) {
//                if (value === callback) {
//                    collection.splice(idx, 1);
//                }
//            });
//        };
//        publish = function (topic) {
//            var res = null;
//            cache[topic] && _.forEachRight(cache[topic], function (value) {
//                value && (res = value.apply({}, Array.prototype.slice.call(arguments, 1)));
//                publish(topic + "_done");
//            });
//            return res;
//        };
//        return {
//            subscribe: subscribe,
//            unsubscribe: unsubscribe,
//            publish: publish
//        };
//    });
//
//    return helenApp;
//
//})();
