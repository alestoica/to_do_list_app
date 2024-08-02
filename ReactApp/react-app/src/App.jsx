import {useEffect, useState} from 'react'
import './App.css'
import {AddTask, DeleteTask, GetTasks, UpdateTask} from "./utils/rest-calls.js";
import TaskTable from "./TaskTable.jsx";

function App() {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        GetTasks()
            .then(events => setTasks(tasks))
            .catch(error => console.log('Fetch error: ', error));
    }, []);

    function addFunction(task) {
        AddTask(task)
            .then(() => GetTasks())
            .then(tasks => setTasks(tasks))
            .catch(error => console.log('Add error: ', error))
    }

    function deleteFunction(taskId) {
        DeleteTask(taskId)
            .then(() => GetTasks())
            .then(tasks => setTasks(tasks))
            .catch(error => console.log('Delete error: ', error))
    }

    function updateFunction(task) {
        UpdateTask(task)
            .then(() => GetTasks())
            .then(tasks => setTasks(tasks))
            .catch(error => console.log('Update error: ', error))
    }

    return (
        <div className="App">
            <h1> To-Do List </h1>
            <TaskTable tasks={tasks} addFunction={addFunction} deleteFunction={deleteFunction} updateFunction={updateFunction}/>
        </div>
    );
}

export default App
