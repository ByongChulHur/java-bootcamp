import type { Customer } from '../types/customer'
import { CustomerCard } from './CustomerCard'
import { EmptyState } from './EmptyState'

export function CustomerList({
                               customers,
                               onEdit,
                             }: {
  customers: Customer[]
  onEdit: (customerId: string) => void
}) {
  if (customers.length === 0) return <EmptyState />
  return (
      <section aria-labelledby="customer-list-title">
        <h2 id="customer-list-title">Customers</h2>
        <ul>
          {customers.map((c) => (
              <li key={c.customerId}>
                <CustomerCard customer={c} onEdit={onEdit} />
              </li>
          ))}
        </ul>
      </section>
  )
}