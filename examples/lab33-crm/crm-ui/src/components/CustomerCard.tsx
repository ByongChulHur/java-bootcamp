import type { Customer } from '../types/customer'
import { StatusBadge } from './StatusBadge'

export function CustomerCard({
  customer,
  onEdit,
}: {
  customer: Customer
  onEdit: (customerId: string) => void
}) {
  const headingId = `customer-${customer.customerId}`
  return (
      <article
          className="card"
          data-testid={`card-${customer.customerId}`}
          aria-labelledby={headingId}
      >
        <h3 id={headingId}>{customer.fullName}</h3>
        <StatusBadge status={customer.status} />
        <p>
          <a href={`mailto:${customer.email}`}>{customer.email}</a>
        </p>
        <button type="button" onClick={() => onEdit(customer.customerId)}>
          Edit
        </button>
      </article>
  )
}
