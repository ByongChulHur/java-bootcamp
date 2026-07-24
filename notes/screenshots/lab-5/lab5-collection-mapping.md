# Collection Mapping — Library Management System

| `books` | `ArrayList<Book>` | Order matters (insertion order), duplicates possible across categories, so List |
| `members` | `ArrayList<Member>` | Member roster, ordered list is sufficient |
| `bookIds` | `HashSet<String>` | Book IDs must be unique — Set gives fast "already exists?" check |
| `memberIds` | `HashSet<String>` | Member IDs must be unique, same reason as above |
| `borrowRecords` | `HashMap<String, String>` | Represents a key-value relationship ("bookId → memberId"), so Map |
| `categories` | `TreeSet<String>` | Category list needs to be shown in sorted (alphabetical) order, so TreeSet |
| `categoryBookCount` | `TreeMap<String, Integer>` | Key-value pair ("category → count") that also needs sorted order, so TreeMap |
| `borrowHistory` | `ArrayList<BorrowRecord>` | Records must be kept in the order events happened, so List |
| `borrowFrequency` | `HashMap<String, Integer>` | Key-value pair ("bookId → borrow count"), no sorting needed, so HashMap |