import React, { useState } from 'react'

const AddPermissionForm = props => {
    const initialFormState = { id: null, name: ''}
    const [ permission, setPermission ] = useState(initialFormState)

    const handleInputChange = event => {
        const { name, value } = event.target

        setPermission({ ...permission, [name]: value })
    }

    return (
        <form
            onSubmit={event => {
                event.preventDefault()
                if (!permission.name) return

                props.addPermission(permission)
                setPermission(initialFormState)
            }}
        >
            <label>Name</label>
            <input type="text" name="name" value={permission.name} onChange={handleInputChange} />
            <button>Add new Permission</button>
        </form>
    )
}

export default AddPermissionForm