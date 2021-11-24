$(document).ready(function () {
    console.log(window.location.protocol);
    var ssl = 'https://www.ur-home.coffee';
    if (window.location.protocol === 'https:') {
    } else {
        if (window.location.origin === 'http://www.ur-home.coffee') {
            location.href = ssl;
        } else if (window.location.origin === 'http://www.ur-home.coffee') {
            location.href = ssl;
        } else if (window.location.origin === 'http://urcoffee.gabia.io') {
            location.href = ssl;
        } else if (window.location.origin === 'http://urcoffee.gabia.io') {
            location.href = ssl;
        } else {
            console.log('localhost:8080 or http protocol executed');
        }
    }
});