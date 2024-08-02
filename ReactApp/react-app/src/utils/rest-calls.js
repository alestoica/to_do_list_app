import { BASE_URL } from "./consts.js";

function status(response) {
    console.log('response status ' + response.status);

    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
    } else {
        return Promise.reject(new Error(response.statusText))
    }
}

function json(response) {
    return response.json()
}

export function GetTasks() {
    let headers = new Headers();
    headers.append('Accept', 'application/json');

    let init = {
        method: 'GET',
        headers: headers,
        mode: 'cors'
    };

    let request = new Request(BASE_URL, init);

    console.log('Before fetch GET for ' + BASE_URL);

    return fetch(request)
        .then(status)
        .then(json)
        .then(data=> {
            console.log('Request succeeded with JSON response: ', data);
            return data;
        }).catch(error => {
            console.log('Request failed with error: ', error);
            return Promise.reject(error);
        });
}

export function DeleteTask(taskId) {
    let headers = new Headers();
    headers.append("Accept", "application/json");

    let init = {
        method: 'DELETE',
        headers: headers,
        mode: 'cors'
    };

    let deleteURL = BASE_URL + '/' + taskId;

    let request = new Request(deleteURL, init);

    console.log('Before fetch DELETE for ' + deleteURL);

    return fetch(request)
        .then(status)
        .then(response => {
            console.log('Request succeeded with status: ', response.status);
            return response.text();
        }).catch(error => {
            console.log('Request failed with error: ', error);
            return Promise.reject(error);
        });
}

export function AddTask(task) {
    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type","application/json");

    let init = {
        method: 'POST',
        headers: headers,
        mode: 'cors',
        body: JSON.stringify(task)
    };

    let request = new Request(BASE_URL, init);

    console.log('Before fetch POST for ' + BASE_URL + ' with task ' + JSON.stringify(task));

    return fetch(request)
        .then(status)
        .then(response => {
            console.log('Request succeeded with status: ', response.status);
            return response.text();
        }).catch(error => {
            console.log('Request failed with error: ', error);
            return Promise.reject(error);
        });
}

export function UpdateTask(task) {
    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let init = {
        method: 'PUT',
        headers: headers,
        mode: 'cors',
        body: JSON.stringify(task)
    };

    let updateURL = BASE_URL;

    let request = new Request(updateURL, init);

    console.log('Before fetch PUT for ' + updateURL + ' with task ' + JSON.stringify(task));

    return fetch(request)
        .then(status)
        .then(response => {
            console.log('Request succeeded with status: ', response.status);
            return response.text();
        }).catch(error => {
            console.log('Request failed with error: ', error);
            return Promise.reject(error);
        });
}