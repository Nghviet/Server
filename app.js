var WebSocketServer = require('websocket').server;
var fs = require('fs');
var http = require('http');
var server = http.createServer(function(request, response) {});
wsServer = new WebSocketServer({
  httpServer: server
});


server.listen(3000, function() { 
	console.log("Listening");
});

wsServer.on('request', function(request) {
  var connection = request.accept(null, request.origin);

  connection.on('message', function(message) {
    if (message.type === 'utf8') {
    	console.log(message);
    	if(message.utf8Data === 'update') {
    		fs.readFile('data','utf8',(err,data) =>{
    			connection.sendUTF(data);
    		})
    	}
    }
  });

  connection.on('close', function(connection) {
    // close user connection
  });
});
