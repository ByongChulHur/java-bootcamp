# Lab 33 — Component notes

## List keys
`customerId` is used as the React key instead of array index because
customerId is stable and unique to each customer. If the list order
changes (sort, filter, insert), an index-based key would cause React
to reuse the wrong component state, leading to UI bugs where the
wrong card appears to belong to the wrong customer.

## A11y
- StatusBadge renders visible text (e.g. "Active") instead of relying
  on color alone, so colorblind users and screen reader users can
  still understand the status.
- CustomerCard uses `aria-labelledby` tied to its `<h3>` heading so
  screen readers announce which customer the card belongs to.
- CustomerForm uses `htmlFor`/`id` pairs on every input so labels are
  programmatically associated, enabling `getByLabelText` queries and
  proper screen reader announcements.