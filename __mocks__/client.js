// const body = {"_embedded":{"employees": [
//     {"firstName": "Chavoris",
//     "lastName":"Lawson",
//     "description": "Trillionaire",
//     "manager":{"name": "chavo", "roles":["ROLE_MANAGER"]}},
//     {"firstName": "Traci",
//     "lastName":"Robins",
//     "description": "Billionaire",
//     "manager":{"name": "chavo", "roles":["ROLE_MANAGER"]}},
//     {"firstName": "Destiny",
//     "lastName":"Lawson",
//     "description": "Millionaire",
//     "manager":{"name": "chavo", "roles":["ROLE_MANAGER"]}}]}
// };

// const chavoBody = {
//   Body: {
//     //may need to rename body to entity
//     firstName: "Chavoris",
//     lastName: "Lawson",
//     description: "Trillionaire",
//     manager: {
//       name: "chavo",
//       roles: ["ROLE_MANAGER"],
//     },
//     _links: {
//       self: {
//         href: "http://localhost/api/employees/1",
//       },
//       employee: {
//         href: "http://localhost/api/employees/1",
//       },
//     },
//   },
// };

const Body = {
  _embedded: {
    employees: [
      {
        firstName: "Chavoris",
        lastName: "Lawson",
        description: "Billionaire",
        manager: {
          name: "chavo",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/1",
          },
          employee: {
            href: "http://localhost/api/employees/1",
          },
        },
      },
      {
        firstName: "Bilbo",
        lastName: "Baggins",
        description: "burglar",
        manager: {
          name: "chavo",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/2",
          },
          employee: {
            href: "http://localhost/api/employees/2",
          },
        },
      },
      {
        firstName: "Gandalf",
        lastName: "the Grey",
        description: "wizard",
        manager: {
          name: "chavo",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/3",
          },
          employee: {
            href: "http://localhost/api/employees/3",
          },
        },
      },
      {
        firstName: "Samwise",
        lastName: "Gamgee",
        description: "gardener",
        manager: {
          name: "traci",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/4",
          },
          employee: {
            href: "http://localhost/api/employees/4",
          },
        },
      },
      {
        firstName: "Meriadoc",
        lastName: "Brandybuck",
        description: "pony rider",
        manager: {
          name: "traci",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/5",
          },
          employee: {
            href: "http://localhost/api/employees/5",
          },
        },
      },
      {
        firstName: "Peregrin",
        lastName: "Took",
        description: "pipe smoker",
        manager: {
          name: "traci",
          roles: ["ROLE_MANAGER"],
        },
        _links: {
          self: {
            href: "http://localhost/api/employees/6",
          },
          employee: {
            href: "http://localhost/api/employees/6",
          },
        },
      },
    ],
  },
  _links: {
    self: {
      href: "http://localhost/api/employees?page=0&size=20",
    },
    profile: {
      href: "http://localhost/api/profile/employees",
    },
  },
  page: {
    size: 20,
    totalElements: 6,
    totalPages: 1,
    number: 0,
  },
};

const apiBody = {
  "_links" : {
    "organizers" : {
      "href" : "http://localhost:8080/api/organizers{?page,size,sort}",
      "templated" : true
    },
    "orders" : {
      "href" : "http://localhost:8080/api/orders{?page,size,sort}",    
      "templated" : true
    },
    "employees" : {
      "href" : "http://localhost:8080/api/employees{?page,size,sort}", 
      "templated" : true
    },
    "events" : {
      "href" : "http://localhost:8080/api/events{?page,size,sort}",    
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile"
    }
  }
}

const rootPath = "/api";

export default function rest(obj) {
  if (obj.path === rootPath && obj["method"] === "GET") {
    console.log(apiBody);
    return new Promise((resolve, reject) =>{
      process.nextTick(() =>
        apiBody
        ? resolve(apiBody)
        : reject({
          error : 'invalid operation',
        }),);
    });
    //return apiBody;
  }
  if (obj["method"] === "GET" && obj.path==="http://localhost:8080/api/employees{?page,size,sort}") {
    console.log(Body)
    return new Promise((resolve, reject) =>{
      process.nextTick(() =>
        apiBody
        ? resolve(Body)
        : reject({
          error : 'invalid operation',
        }),);
    });
    //return Body;
  }
  if (obj.method === "POST" && obj.hasOwnProperty("entity")) {
    console.log(obj.entity);
    return new Promise((resolve, reject) =>{
      process.nextTick(() =>
        obj.entity
        ? resolve(obj.entity)
        : reject({
          error : 'invalid operation',
        }),);
    });
    
  }
}
