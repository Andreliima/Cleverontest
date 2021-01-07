import React from 'react'

const PermissionsTable = props => (
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        {props.permissions.length > 0 ? (
            props.permissions.map(permission => (
                <tr key={permission.id}>
                    <td>{permission.name}</td>
                    <td>{permission.username}</td>
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