import React, {useState, useEffect, Fragment} from 'react'
import AddPermissionForm from "./components/AddPermissionForm";
import PermissionsTable from "./components/PermissionsTable";
import EditPermissionForm from "./components/EditPermissionForm";
import axios from "axios";

const App = () => {
    const url = 'http://localhost:8080/';


    useEffect(() => {
        getAllPermissions();
    }, []);

    const initialFormState = {id: null, name: ''}

    // Setting state
    const [permissions, setPermissions] = useState([])
    const [currentPermission, setCurrentPermission] = useState(initialFormState)
    const [editing, setEditing] = useState(false)

    // CRUD operations
    const getAllPermissions = () => {
        axios.get(`${url}api/permissions/`)
            .then((response) => {
                setPermissions(response.data);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    const addPermission = permission => {
        axios.post(`${url}api/permissions/`, permission)
            .then((response) => {
                setPermissions([...permissions, response.data]);
            })
            .catch(error => console.error(`Error: ${error.response.data.errors ?? error.response.data ?? error.response ?? "No idea"}`));
    }

    const deletePermission = id => {
        setEditing(false)

        axios.delete(`${url}api/permissions/${id}`)
            .then((response) => {
                const old = permissions.find(permission => permission.id === id);
                if (old.parent){
                    let oldParent = permissions.find(permission => permission.id === old.parent.id);
                    oldParent.subPermissions = oldParent.subPermissions.filter(sub => sub.id !== old.id);
                }
                setPermissions(permissions.filter(permission => permission.id !== id));
            })
            .catch(error => console.error(`Error: ${error.response.data}`));
    }

    const updatePermission = (id, updatedPermission) => {
        setEditing(false)

        axios.put(`${url}api/permissions/${id}`, {
            name: updatedPermission.name,
            parentId: updatedPermission.parent ? updatedPermission.parent.id : null
        })
            .then((response) => {
                console.log("Response:", response.data)
                getAllPermissions();
            })
            .catch(error => console.error(`Error: ${error.response.data.errors}`));
    }

    const addSubPermission = (id, subPermission) => {
        axios.post(`${url}api/permissions/${id}`, subPermission)
            .then((response) => {
                getAllPermissions();
            })
            .catch(error => console.error(`Error: ${error.response.data.errors}`));
    }

    const editPermission = permission => {
        setEditing(true)

        setCurrentPermission({...permission})
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
                                allPermissions={permissions}
                                addSubPermission={addSubPermission}
                                updatePermission={updatePermission}
                            />
                        </Fragment>
                    ) : (
                        <Fragment>
                            <h2>Add Permission</h2>
                            <AddPermissionForm addPermission={addPermission}/>
                        </Fragment>
                    )}
                </div>
                <div className="flex-large">
                    <h2>View Permissions</h2>
                    <PermissionsTable permissions={permissions} editPermission={editPermission}
                                      deletePermission={deletePermission}/>
                </div>
            </div>
        </div>
    )
}

export default App
