import { useState } from 'react'
import { AppLayout } from './components/AppLayout'
import { CustomerToolbar } from './components/CustomerToolbar'
import { CustomerList } from './components/CustomerList'
import { CustomerForm } from './components/CustomerForm'
import { LoadingState } from './components/LoadingState'
import { ErrorState } from './components/ErrorState'
import { seedCustomers } from './data/seedCustomers'
import type { CustomerDraft } from './types/customer'

const emptyDraft: CustomerDraft = {
    fullName: '',
    email: '',
    status: 'PROSPECT',
}

export default function App() {
    const [customers] = useState(seedCustomers)
    const [viewState, setViewState] = useState<'idle' | 'loading' | 'error'>('idle')

    return (
        <AppLayout>
            <div>
                <button type="button" onClick={() => setViewState('loading')}>
                    Show Loading
                </button>
                <button type="button" onClick={() => setViewState('error')}>
                    Show Error
                </button>
                <button type="button" onClick={() => setViewState('idle')}>
                    Show List
                </button>
            </div>

            {viewState === 'loading' && <LoadingState />}
            {viewState === 'error' && (
                <ErrorState message="Failed to load customers. Please retry." />
            )}
            {viewState === 'idle' && (
                <>
                    <CustomerToolbar
                        onAdd={() => console.log('add', 'lab-request-001')}
                    />
                    <CustomerList
                        customers={customers}
                        onEdit={(id) => console.log('edit', id, 'lab-request-001')}
                    />
                    <CustomerForm
                        draft={emptyDraft}
                        onChange={() => { /* TODO: wired in Lab 34 */ }}
                        onSubmit={() => console.log('submit', 'lab-request-001')}
                    />
                </>
            )}
        </AppLayout>
    )
}