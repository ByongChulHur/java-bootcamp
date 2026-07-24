# Collection Mapping — Library Management System

| 1 | `books` |`ArrayList<>` | Since the order matters and duplicates is possible across categories it have to be ArrayList |
| 2 | `members` |`ArrayList<>` | Since the roster just have to be ordered and there's no need for unique check here it will be ArrayList |
| 3 | `bookIds` |`HashSet<>` | Since book IDs have to be unique and the order does not matter it have to be HashSet |
| 4 | `memberIds` |`HashSet<>` | Since member IDs have to be unique for the same reason as bookIds, it will be HashSet |
| 5 | `borrowRecords` | `HashMap<>` | Since its bookId → memberId which is key-value related it have to be map and since the order is not sorted it will be HashMap |
| 6 | `categories` | `TreeSet<>` | Since the categories have to be unique and have to be sorted by alphabetic order it will be TreeSet |
| 7 | `categoryBookCount` | `TreeMap<>` | Since we are checking category → count it is key-value which means it have to be map and since it have to be sorted it will be TreeMap |
| 8 | `borrowHistory` | `ArrayList<>` | Since the borrow history has to be recorded in the exact order it happened, it have to be ArrayList |
| 9 | `borrowFrequency` | `HashMap<>` | Since its bookId → borrow count which is key-value related and sorting isn't needed here, it will be HashMap |