# AmountTracker

## Introduction
The purpose of the application is to record personal incomes and expenses. The application
is provided for all users who want to track all their spending or income. They can record 
what they spend on, such as food, shopping, or their sources of income, such as salary. 
This intrigued me because I wanted to understand my spending habits and sometimes, I did not know 
what I spend on, which makes me upset. I wanted to record my spending list, and also the sources of income,
so built this project.

In our life, we expense almost every day. As a result, it can be difficult for people to manage their 
expenses and income. The application allows users to add their income and expenses with their categories, and 
to be able to check their total amount and total expenses and income amount, and to print the total amount lists. 
And it allows people to better understand their spending habits and keeps their lives more organized.



## **User Stories**
- As a user, I want to be able to add an amount (both income and expense amount).
- As a user, I want to be able to select the categories of the amount, and add them into the list of the total amounts.
- As a user, I want to be able to view the total amount, total income amount, and total expense amount.
- As a user, I want to be able to view the list of the total amounts and their categories.
- As a user, I want to be able to save the list of amounts to a file.
- As a user, I want to have the option to load a list of amounts from a file when I start the application.

**Phase 4: Task 2**
> Thu Mar 31 23:13:22 PDT 2022
<br>Add income amount SCHOLARSHIP 200.0 to total list of amount!
> <br><br>Thu Mar 31 23:13:22 PDT 2022
<br>Add expense amount ENTERTAINMENT -100.0 to total list of amount!
> <br><br>Thu Mar 31 23:13:22 PDT 2022
<br>Add expense amount RENT -500.0 to total list of amount!
> <br><br>Thu Mar 31 23:13:33 PDT 2022
<br>Total amount list was printed!
> <br><br>Thu Mar 31 23:13:47 PDT 2022
<br>Add expense amount SHOPPING -100.0 to total list of amount!


**Phase 4: Task 3**
<br>*Reflect on the UML design diagram*

- Looking at the UML design diagram, there are some classes that have similar behaviour. If I have more time, I would reduce the 
duplicate methods between these classes. For instance, the IncomeAmountEntryAction class and 
ExpenseAmountEntryAction class, which are both inner classes of AddAmountPane. They have similar behaviour in that they 
both represent input amounts frame (one for income and the other for expenses). They have some methods in common, 
such as the JFieldArea part. However, they also have some different behaviors, for example they need to pass the different 
button components when passing into the confirmButtonAction. Therefore, I would create an abstract class containing the
same functionality methods and extend both classes to the abstract class.
- Also, the association on AmountTracker is complicated. I would get rid of some association on AmountTracker, for instance, 
the association between the AmountTracker and TrackerGUI. I would move all related methods into AmountTrackerList. 

### ***References for Images***
*All images from https://www.flaticon.com*
> - <a href="https://www.flaticon.com/free-icons/invoice" title="invoice icons">Invoice icons created by Kiranshastry - Flaticon</a>
> - <a href="https://www.flaticon.com/free-icons/list" title="list icons">List icons created by Kiranshastry - Flaticon</a>
> - <a href="https://www.flaticon.com/free-icons/money" title="money icons">Money icons created by Freepik - Flaticon</a>
> - <a href="https://www.flaticon.com/free-icons/expense" title="expense icons">Expense icons created by surang - Flaticon</a>
> - <a href="https://www.flaticon.com/free-icons/refresh" title="refresh icons">Refresh icons created by Roundicons - Flaticon</a>
> - <a href="https://www.flaticon.com/free-icons/save" title="save icons">Save icons created by Those Icons - Flaticon</a>

