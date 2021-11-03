# library-mgmt
Divided the components of Library Management into 4 classes :- 
- **Book** - It will have information and operations related to a book.
- **BookStoke** - It will have the stock of books present in the library and operations on it.
- **User** - It will have information of a user and books that a user have borrowed.
- **Library** - it will have main operation of issuing and returning a book to the library from any user.

Design and development has been done considering DRY, KISS and SOLID principles.

All the below test cases are added in "library-mgmt/src/test/java/com/library/mgmt/" to check and verify the functionality.

**Stories**
1. **User can view books in library**<br>
   Scenario : As a User<br>
   I want to see the books present in the library<br>
   So that I can chose which book to borrow<br><br>
   
   Given , there are no books in the library<br>
   When , I view the books in the library<br>
   Then , I see an empty library<br><br>
   
   Given , there are books in the library<br>
   When , I view the books in the library<br>
   Then , I see the list of books in the library<br><br>

2. **User can borrow a book from the library**<br>
   Given , there are books in the library<br>
   When , I choose a book to add to my borrowed list<br>
   Then , the book is added to my borrowed list<br>
   And , the book is removed from the library<br>
   Note:<br>
   a. Each User has a borrowing limit of 2 books at any point of time<br><br>

3. **User can borrow a copy of a book from the library**<br>
   Given , there are more than one copy of a book in the library<br>
   When , I choose a book to add to my borrowed list<br>
   Then , one copy of the book is added to my borrowed list<br>
   And , the library has at least one copy of the book left<br><br>
   Given , there is only one copy of a book in the library<br>
   When , I choose a book to add to my borrowed list<br>
   Then , one copy of the book is added to my borrowed list<br>
   And , the book is removed from the library<br>
   Note:
   a. Only 1 copy of a book can be borrowed by a User at any point of time<br><br>

4. **User can return books to the library**<br>
   Given , I have 2 books in my borrowed list<br>
   When , I return one book to the library<br>
   Then , the book is removed from my borrowed list<br>
   And , the library reflects the updated stock of the book<br><br>
   Given , I have 2 books in my borrowed list<br>
   When , I return both books to the library<br>
   Then , my borrowed list is empty<br>
   And , the library reflects the updated stock of the books<br>