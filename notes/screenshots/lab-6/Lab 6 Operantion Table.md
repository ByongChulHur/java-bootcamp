# Lab 6 — Stream Operations Table & Reflection

## Stream Operations Table

| Operation / API | Used? | Where (method / menu) | Notes |
| --- | :---: | --- | --- |
| Lambda `forEach` | Y | demonstrateLambdas (menu 10) | Print name/salary/department per employee |
| `Predicate` | Y | demonstrateFunctionalInterfaces (menu 11) | highEarner: salary > 100,000 |
| `Function` | Y | demonstrateFunctionalInterfaces (menu 11) | employeeSummary: name + department string |
| `Consumer` | Y | demonstrateFunctionalInterfaces (menu 11) | printRating: prints name + rating |
| `Supplier` | Y | demonstrateFunctionalInterfaces (menu 11) | topSample: supplies highest-paid employee |
| `filter` | Y | displayHighSalaryEmployees, displayItEmployees, displayActiveEmployees, displayFilteredItTopPerformers (menu 7, 13, 14) | Single and chained filters |
| `map` | Y | demonstrateMapping (menu 15) | Projected to name / salary / department |
| `sorted` | Y | demonstrateSorting (menu 16) | Salary asc/desc, name, experience desc |
| `distinct` | Y | displayDistinctDepartments (menu 17) | Unique department names |
| `limit` / `skip` | Y | displayTopAndNextSalaries (menu 18) | Top 5 and next 5 salaries |
| `count` | Y | displayCounts (menu 19) | Total, IT, active, high-salary counts |
| `reduce` | Y | displayReductions (menu 3) | Highest/lowest salary via Double::max / Double::min |
| `collect(toList/toSet)` | Y | demonstrateCollectors (menu 20) | Active employee list, department set |
| `groupingBy` | Y | displayGroupedEmployees, displayDepartmentStatistics (menu 2, 6) | Group by department, with counting/summarizing downstream |
| `partitioningBy` | Y | displayPartitionedEmployees (menu 3) | Split by salary > 100,000 |
| `summarizingDouble` | Y | displaySummaryStatistics, displayDashboard (menu 3, 8) | Count/sum/min/max/average in one pass |
| `Optional` (`max` / `ifPresent`) | Y | displayHighestPaidEmployeeOptional, findTopPerformer, displayDashboard (menu 5, 8) | ifPresentOrElse for safe null handling |
| Method references | Y | Throughout (Employee::getName, Employee::isActive, etc.) | Used instead of lambdas for single getter calls |
| Dashboard composed report | Y | displayDashboard (menu 8) | Combines stats, grouping, Optional|