import React, {useState} from "react";

function ResultRow({task, deleteFunction, updateFunction}) {
    const [updatedTaskDescription, setUpdatedTaskDescription] = useState("");
    const [isModified, setIsModified] = useState(false);

    function handleDelete() {
        deleteFunction(task.id);
    }

    function handleUpdate() {
        task.description = updatedTaskDescription;
        updateFunction(task);
    }

    const handleEdit = () => {
        setIsModified(true);
    };

    const handleCancel = () => {
        setIsModified(false);
        setUpdatedTaskDescription(task.description);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        handleUpdate();
        setIsModified(false);
    };

    return (
        <tr>
            <td> {task.id} </td>

            <td>
                {isModified ? (
                    <input
                        type="text"
                        value={updatedTaskDescription}
                        onChange={(e) => setUpdatedTaskDescription(e.target.value)}
                    />
                ) : (
                    task.description
                )}
            </td>

            <td>
                {isModified ? (
                    <>
                        <button onClick={handleSubmit}>Save</button>
                        <button onClick={handleCancel}>Cancel</button>
                    </>
                ) : (
                    <>
                        <button onClick={handleDelete}>Delete</button>
                        <button onClick={handleEdit}>Update</button>
                    </>
                )}
            </td>

        </tr>
    )
}

function NewTaskRow({addFunction, setIsAdding}) {
    const [newTaskDescription, setNewTaskDescription] = useState("");

    const handleAdd = () => {
        const newTask = {
            description: newTaskDescription
        };
        addFunction(newTask);
        setIsAdding(false);
    };

    const handleCancel = () => {
        setIsAdding(false);
    };

    return (
        <tr>
            <td>New</td>
            <td>
                <input
                    type="text"
                    value={newTaskDescription}
                    onChange={(e) => setNewTaskDescription(e.target.value)}
                />
            </td>
            <td>
                <button onClick={handleAdd}>Save</button>
                <button onClick={handleCancel}>Cancel</button>
            </td>
        </tr>
    );
}

export default function TaskTable({tasks, addFunction, deleteFunction, updateFunction}) {
    const [isAdding, setIsAdding] = useState(false);

    let rows = [];

    tasks.forEach(function (task) {
       rows.push(<ResultRow task={task} deleteFunction={deleteFunction} updateFunction={updateFunction}/>)
    });

    return (
        <div className="TaskTable">
            <table className="center">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {rows}
                    {isAdding && <NewTaskRow addFunction={addFunction} setIsAdding={setIsAdding}/>}
                </tbody>
            </table>

            <button onClick={() => setIsAdding(true)}>Add Task</button>
        </div>
    );
}