import type { CustomerDraft, CustomerStatus } from '../types/customer'

export function CustomerForm({
                                 draft,
                                 onChange,
                                 onSubmit,
                             }: {
    draft: CustomerDraft
    onChange: (next: CustomerDraft) => void
    onSubmit: () => void
}) {
    return (
        <form
            onSubmit={(e) => {
                e.preventDefault()
                onSubmit()
            }}
        >
            <div>
                <label htmlFor="fullName">Full name</label>
                <input
                    id="fullName"
                    type="text"
                    value={draft.fullName}
                    onChange={(e) => onChange({ ...draft, fullName: e.target.value })}
                />
            </div>

            <div>
                <label htmlFor="email">Email</label>
                <input
                    id="email"
                    type="email"
                    value={draft.email}
                    onChange={(e) => onChange({ ...draft, email: e.target.value })}
                />
            </div>

            <div>
                <label htmlFor="status">Status</label>
                <select
                    id="status"
                    value={draft.status}
                    onChange={(e) =>
                        onChange({ ...draft, status: e.target.value as CustomerStatus })
                    }
                >
                    <option value="PROSPECT">Prospect</option>
                    <option value="ACTIVE">Active</option>
                    <option value="CLOSED">Closed</option>
                </select>
            </div>

            <button type="submit">Save</button>
            <button type="button" onClick={onSubmit}>Cancel</button>
        </form>
    )
}