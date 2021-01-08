import React from 'react'
import {Badge} from "reactstrap";

const PermissionsTable = props => (
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Subpermissions</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        {props.permissions.length > 0 ? (
            props.permissions.map(permission => (
                <tr key={permission.id}>
                    <td>{permission.name}</td>
                    <td>
                        {permission.subPermissions ? permission.subPermissions.map(sub => (<Badge color="info" style={{marginLeft: "2px", marginRight: "2px"}}> {sub.name} </Badge>)) : ""}
                    </td>
                    <td>
                        <button
                            onClick={() => {
                                props.editPermission(permission)
                            }}
                            className="button muted-button"
                        >
                            Edit
                        </button>
                        <button
                            onClick={() => props.deletePermission(permission.id)}
                            className="button muted-button"
                        >
                            Delete
                        </button>
                    </td>
                </tr>
            ))
        ) : (
            <tr>
                <td colSpan={3}>No Permissions</td>
            </tr>
        )}
        </tbody>
    </table>
)

export default PermissionsTable