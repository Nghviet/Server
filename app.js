var WebSocket = require('ws');
var fs = require('fs');
var http = require('http');
var express = require('express');
var app = express();

var server = http.createServer(app);
wsServer = new WebSocket.Server({server});

server.listen(3000, function() { 
	console.log("Listening");
});

var next = false;

// Java client
wsServer.on('connection', (ws) => {
  	ws.on('message', function(message) {
  		{
	    	console.log(message);
	    	if(next == true) {
	    		next = false;
	    		fs.writeFile('data', message,(err) =>{});
	    		console.log("write complete");
	    	}

	    	if(message == 'get') {
	    		console.log('sending response');
	    		fs.readFile('data','utf8',(err,data) =>{
	    			ws.send(data);
	    		})
	    	}
	    	
	    	if(message == 'update') {
	    		next = true;
	    		console.log("new data");
	    	}
	    }
  	});

  	ws.on('close', function(ws) {
    // close user connection
  	});
});

// Webbase client
var io = require('socket.io')(server);
io.on('connection', (socket) =>{
	console.log("Incomming web");
});