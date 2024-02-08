module.exports = function follow(api, rootPath, relArray) {
	const root = api({
		method: 'GET',
		path: rootPath
	});
//returns the response from localhost:8080/api not localhost:8080/api/employees
//hitting the data rest repository which is why all of the other models are also showing up 
	return relArray.reduce(function(root, arrayItem) {
		const rel = typeof arrayItem === 'string' ? arrayItem : arrayItem.rel;
		console.log(arrayItem)
		console.log(rel)
		return traverseNext(root, rel, arrayItem);
	}, root);

	function traverseNext (root, rel, arrayItem) {
		return root.then(function (response) {
			if (hasEmbeddedRel(response.entity, rel)) {
				console.log("has embedded rel")
				console.log(response.entity._embedded[rel])
				console.log(response.entity)
				return response.entity._embedded[rel];
			}

			if(!response.entity._links) {
				return [];
			}

			if (typeof arrayItem === 'string') {
				console.log("is string")//create http://localhost:8080/api/employees{?page,size,sort}
				console.log(response.entity._links[rel].href)
				console.log(response.entity)
				return api({
					method: 'GET',
					path: response.entity._links[rel].href
				});
			} else {
				console.log(response.entity._links[rel].href)//employees http://localhost:8080/api/employees{?page,size,sort}
				console.log(response.entity)
				return api({
					method: 'GET',
					path: response.entity._links[rel].href,
					params: arrayItem.params
				});
			}
		});
	}

	function hasEmbeddedRel (entity, rel) {
		return entity._embedded && entity._embedded.hasOwnProperty(rel);
	}
};