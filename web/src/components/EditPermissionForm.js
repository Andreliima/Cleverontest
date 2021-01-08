import React, {useState, useEffect} from 'react'

const EditUserForm = props => {
    const [permission, setPermission] = useState(props.currentPermission)
    const [sub, setSub] = useState({})

    useEffect(
        () => {
            setPermission(props.currentPermission);
        },
        [props]
    )
    // You can tell React to skip applying an effect if certain values haven’t changed between re-renders. [ props ]

    const handleInputChange = event => {
        const {name, value} = event.target;

        setPermission({...permission, [name]: value})
    }

    const handleSubpermissionInputChange = event => {
        const {name, value} = event.target;

        setSub({...sub, [name]: value})
    }

    const handleSelectionChange = event => {
        const {name, value} = event.target;
        if (value === "0") {
            setPermission({...permission, parent: null});
        } else {
            const parent = props.allPermissions.find(p => p.id.toString() === value)
            setPermission({...permission, parent: parent});
        }

    }

    return (
        <form
            onSubmit={event => {
                event.preventDefault();
                props.updatePermission(permission.id, permission);
            }}
        >
            <label>Name</label>
            <input type="text" name="name" value={permission.name} onChange={handleInputChange}/>
            <label>Parent</label>
            <select type="text" name="parentId" value={permission.parent ? permission.parent.id : "0"}
                    onChange={handleSelectionChange}>
                <option value="0">No parent permission</option>
                {props.allPermissions.map(p => {
                    return (permission.id !== p.id) ? <option value={p.id}>{p.name}</option> : ""
                })}
            </select>
            <button>Update user</button>
            <button onClick={() => props.setEditing(false)} className="button muted-button">
                Cancel
            </button>
            <label>Subpermission name</label>
            <input type="text" name="name" value={sub.name} onChange={handleSubpermissionInputChange}/>
            <button onClick={() => props.addSubPermission(permission.id, sub)}>
                Add new subpermission
            </button>
        </form>
)
}

export default EditUserForm