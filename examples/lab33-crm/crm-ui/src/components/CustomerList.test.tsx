import { render, screen, within } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { CustomerList } from './CustomerList'
import { seedCustomers } from '../data/seedCustomers'

describe('CustomerList', () => {
  it('renders fixture customers by name', () => {
    render(<CustomerList customers={seedCustomers} onEdit={() => {}} />)
    expect(screen.getByText(/Amina Khan/i)).toBeInTheDocument()
    expect(screen.getByText(/Ravi Singh/i)).toBeInTheDocument()
  })

  it('shows empty state when there are no customers', () => {
    render(<CustomerList customers={[]} onEdit={() => {}} />)
    expect(screen.getByRole('status')).toHaveTextContent(/no customers yet/i)
  })

  it('calls onEdit with the correct customerId when Edit is clicked', async () => {
    const user = userEvent.setup()
    const onEdit = vi.fn()
    render(<CustomerList customers={seedCustomers} onEdit={onEdit} />)

    const aminaCard = screen.getByTestId('card-CUS-1001')
    await user.click(within(aminaCard).getByRole('button', { name: 'Edit' }))

    expect(onEdit).toHaveBeenCalledWith('CUS-1001')
  })
})