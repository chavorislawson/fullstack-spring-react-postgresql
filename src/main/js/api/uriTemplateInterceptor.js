// requirejs.config({
// 	paths:{
// 		// There's a number of different ways I can do this. Either I figure out this requirejs. config way or
// 		//rewrite this class so it doesn't use this require syntax and just returns the interceptor
// 		//similar to the client class.
// 	}
// })
'use strict';

const interceptor = require('rest/interceptor');

module.exports = function() {

	return interceptor({
		request: function (request /*, config, meta */) {
			/* If the URI is a URI Template per RFC 6570 (https://tools.ietf.org/html/rfc6570), trim out the template part */
			if (request.path.indexOf('{') === -1) {
				return request;
			} else {
				request.path = request.path.split('{')[0];
				return request;
			}
		}
	});

};