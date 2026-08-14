export function CustomerToolbar({ onAdd }: { onAdd: () => void }) {
    return (
        <div>
            <button type="button" onClick={onAdd}>
                Add customer
            </button>
        </div>
    )
}