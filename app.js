var WebSocketServer = require('websocket').server;
var http = require('http');

var server = http.createServer(function(request, response) {});
server.listen(3000, function() { 
	console.log("Listening");
});

wsServer = new WebSocketServer({
  httpServer: server
});

wsServer.on('request', function(request) {
  var connection = request.accept(null, request.origin);

  connection.on('message', function(message) {
    if (message.type === 'utf8') {

    }
  });

  connection.on('close', function(connection) {
    // close user connection
  });
});