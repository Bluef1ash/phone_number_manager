require.config({
    baseUrl: jsPath + "applications/",
    paths: {
        css: jsPath + "libraries/requirejs/plugin/css.min",
        domReady: jsPath + "libraries/requirejs/plugin/dom-ready.min",
        jquery: (belowIe9 && jsPath + "libraries/jquery/jquery-1.X.min") || jsPath + "libraries/jquery/jquery-3.X.min",
        bootstrap: jsPath + "libraries/bootstrap.min",
        angular: jsPath + "libraries/angular.min",
        lodash: jsPath + "libraries/lodash/lodash.min",
        layer: jsPath + "libraries/layer/layer",
        chart: jsPath + "libraries/charts/Chart",
        zeroClipboard: jsPath + "libraries/zeroclipboard/ZeroClipboard.min",
        common: jsPath + "libraries/common"
    },
    shim: {
        jquery: {
            exports: "$"
        },
        angular: {
            exports: "angular"
        },
        bootstrap: {
            deps: ["jquery"]
        },
        layer: {
            deps: ["jquery", "css!" + jsPath + "libraries/layer/skin/layer.css"],
            exports: "layer"
        }
    }
});