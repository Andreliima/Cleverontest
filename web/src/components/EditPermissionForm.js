import React, { useState, useEffect } from 'react'

const EditUserForm = props => {
    const [ permission, setPermission ] = useState(props.currentPermission)

    useEffect(
        () => {
            setPermission(props.currentPermission)
        },
        [ props ]
    )
    // You can tell React to skip applying an effect if certain values haven’t changed between re-renders. [ props ]

    const handleInputChange = event => {
        const { name, value } = event.target

        setPermission({ ...permission, [name]: value })
    }

    return (
        <form
            onSubmit={event => {
                event.preventDefault()

                props.updatePermission(permission.id, permission)
            }}
        >
            <label>Name</label>
            <input type="text" name="name" value={permission.name} onChange={handleInputChange} />
            <button>Update user</button>
            <button onClick={() => props.setEditing(false)} className="button muted-button">
                Cancel
            </button>
        </form>
    )
}

export default EditUserForm