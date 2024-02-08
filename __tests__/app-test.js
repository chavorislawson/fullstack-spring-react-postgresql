//import {cleanup, fireEvent, render} from '@testing-library/react';
//import App from '../src/main/js/app';
//import { createRenderer } from 'react-dom/test-utils';

//What do you want to do
//Test crud operations
//Test navigation
//Test update page size
//test individual components methods\

//Think I have to re-write the entire app without calling the internet just for me to test it
//I think I just go to the component itself I'm testing and pass in the mocked things
//promises, async/await, and what are the differences.

//onCreate
//mockUp client.js so it returns the structure I need.
//redo onCreate so it also uses the mockedUp client

import App from '../src/main/js/app';
import React from 'react';
import ReactDOM from 'react-dom';

it('Render without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App/>, div);
});

// jest.mock('../__mocks__/client')
// it('App adds new employee to last page after onCreate. If number of employees is greater than pageSize it creates a new page', () => {
//     expect.assertions();
//     //const component = createRenderer(<CreateDialog attributes={[]} onCreate={""}></CreateDialog>)
//     newEmployee = {"firstName" : "Chavoris",
// "lastName": "Lawson",
// "description": "This is a test"};
//     return app.onCreate(newEmployee).then(data => expect(data).toBe(newEmployee));
// });