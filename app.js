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

var next = false;

wsServer.on('request', function(request) {
  var connection = request.accept(null, request.origin);

  connection.on('message', function(message) {
    if (message.type === 'utf8') {
    	console.log(message);
    	if(next === true) {
    		next = false;
    		fs.writeFile('data', message.utf8Data,(err) =>{});
    		console.log("write complete");
    	}

    	if(message.utf8Data === 'get') {
    		fs.readFile('data','utf8',(err,data) =>{
    			connection.sendUTF(data);
    		})
    	}
    	
    	if(message.utf8Data === 'update') {
    		next = true;
    		console.log("new data");
    	}
    }
  });

  connection.on('close', function(connection) {
    // close user connection
  });
});
