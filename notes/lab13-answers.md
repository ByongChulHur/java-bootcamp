## Reflection Questions

1. Which design decision most affected partner usability?
    One thing that was most helpful decision was naming of soapAction. I used the same pattern for all of operation such as CreateCustomer, UpdateCustomer. By doing this
    future partner can look at the WSDL and understand how toc all each operation without asking or getting confusion.

2. What evidence proves the contract is implementable in Lab 24?
   I ran a PowerShell check on all 10 files that are given for the following lab such as customer.xsd,
   CustomerService.wsdl, and 8 sample XML files, and every file
   returned "OK". I think this clearly shows that XML is well structured.


3. Which failure was hardest to diagnose (namespace vs element name)?
   The first failure experiment was the hardest for me and this is because when I changed
   customer.xsd to customerXX.xsd to make an error in purpose IntelliJ did not show a clear "typo"
   message. Instead, all it did was only showed more warnings, so I had to find the problem
   by myself.