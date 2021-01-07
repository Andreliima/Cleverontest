import React, { useState, useEffect, Fragment } from 'react'
import AddPermissionForm from "./components/AddPermissionForm";
import PermissionsTable from "./components/PermissionsTable";
import EditPermissionForm from "./components/EditPermissionForm";
import axios from "axios";

const App = () => {
    const url = 'localhost:8080/';


    useEffect(() => {
        getAllPermissions();
    })

    const initialFormState = { id: null, name: ''}

    // Setting state
    const [ permissions, setPermissions ] = useState([])
    const [ currentPermission, setCurrentPermission ] = useState(initialFormState)
    const [ editing, setEditing ] = useState(false)

    // CRUD operations
    const getAllPermissions = () => {
        axios.get(`${url}api/permissions/`)
            .then((response) => {
                setPermissions(response);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    const addPermission = permission => {
        permission.id = permission.length + 1
        setPermissions([ ...permissions, permission ])
    }

    const deletePermission = id => {
        setEditing(false)

        setPermissions(permissions.filter(permission => permission.id !== id))
    }

    const updatePermission = (id, updatedPermission) => {
        setEditing(false)

        setPermissions(permissions.map(permission => (permission.id === id ? updatedPermission : permission)))
    }

    const editPermission = permission => {
        setEditing(true)

        setCurrentPermission({ id: permission.id, name: permission.name})
    }

    return (
        <div className="container">
            <h1>Permission Management</h1>
            <div className="flex-row">
                <div className="flex-large">
                    {editing ? (
                        <Fragment>
                            <h2>Edit Permission</h2>
                            <EditPermissionForm
                                editing={editing}
                                setEditing={setEditing}
                                currentPermission={currentPermission}
                                updatePermission={updatePermission}
                            />
                        </Fragment>
                    ) : (
                        <Fragment>
                            <h2>Add Permission</h2>
                            <AddPermissionForm addPermission={addPermission} />
                        </Fragment>
                    )}
                </div>
                <div className="flex-large">
                    <h2>View Permissions</h2>
                    <PermissionsTable permissions={permissions} editPermission={editPermission} deletePermission={deletePermission} />
                </div>
            </div>
        </div>
    )
}

export default App
