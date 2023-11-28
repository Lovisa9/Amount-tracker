package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Represent tests for classes in model
public class AmountTrackerListTest {
    private AmountTrackerList amList;
    private AmountTracker expenseAmount1;
    private AmountTracker expenseAmount2;
    private AmountTracker expenseAmount3;
    private AmountTracker incomeAmount1;
    private AmountTracker incomeAmount2;
    private AmountTracker incomeAmount3;

    //EFFECTS: constructs a AmountTrackerList and new AmountTrackers;
    // run before each test
    @BeforeEach
    public void runBefore() {
        amList = new AmountTrackerList();

        expenseAmount1 = new AmountTracker("FOOD", 100);
        expenseAmount2 = new AmountTracker("SHOPPING", 200);
        expenseAmount3 = new AmountTracker("ENTERTAINMENT", 300);

        incomeAmount1 = new AmountTracker("SALARY", 500);
        incomeAmount2 = new AmountTracker("OTHERS_INCOME", 100);
        incomeAmount3 = new AmountTracker("SCHOLARSHIP", 1000);
    }

    //EFFECTS: test if the constructor is empty
    @Test
    public void testConstructor() {
        assertTrue(amList.isEmpty());
    }

    //EFFECTS: test addAmount method; test add income, expense amount, and
    // test adding at least 3 times income, expense amount
    @Test
    public void addAmount() {
        amList.setInitialAmount(1000);

        // income
        // EFFECTS: add incomeAmount1 into AmountTrackerList, and test
        // for getTotalAmount(), getIncomeAmount(), printTotalList() for
        // each of the method
        amList.addAmount(incomeAmount1);
        assertEquals(1500, amList.getTotalAmount());
        assertEquals(500, amList.getIncomeAmount());
        assertEquals("SALARY 500.0", amList.printTotalList());

        // EFFECTS: add incomeAmount2 into AmountTrackerList, and test for it
        amList.addAmount(incomeAmount2);
        assertEquals(1600, amList.getTotalAmount());
        assertEquals(600, amList.getIncomeAmount());
        assertEquals("SALARY 500.0\nOTHERS_INCOME 100.0", amList.printTotalList());

        // EFFECTS: add incomeAmount3 into AmountTrackerList, and test for it
        amList.addAmount(incomeAmount3);
        assertEquals(2600, amList.getTotalAmount());
        assertEquals(1600, amList.getIncomeAmount());
        assertEquals("SALARY 500.0\nOTHERS_INCOME 100.0\nSCHOLARSHIP 1000.0", amList.printTotalList());

        // expense
        // EFFECTS: add expenseAmount1 into AmountTrackerList, and test
        // for getTotalAmount(), getExpenseAmount(), printTotalList() for
        // each of the method
        amList.addAmount(expenseAmount1);
        assertEquals(2500, amList.getTotalAmount());
        assertEquals(100, amList.getExpenseAmount());
        assertEquals("SALARY 500.0\nOTHERS_INCOME 100.0\nSCHOLARSHIP 1000.0" +
                "\nFOOD -100.0", amList.printTotalList());

        // EFFECTS: add expenseAmount12 into AmountTrackerList, and test for it
        amList.addAmount(expenseAmount2);
        assertEquals(2300, amList.getTotalAmount());
        assertEquals(300, amList.getExpenseAmount());
        assertEquals("SALARY 500.0\nOTHERS_INCOME 100.0\nSCHOLARSHIP 1000.0" +
                "\nFOOD -100.0\nSHOPPING -200.0", amList.printTotalList());

        // EFFECTS: add expenseAmount12 into AmountTrackerList, and test for it
        amList.addAmount(expenseAmount3);
        assertEquals(2000, amList.getTotalAmount());
        assertEquals(600, amList.getExpenseAmount());
        assertEquals("SALARY 500.0\nOTHERS_INCOME 100.0\nSCHOLARSHIP 1000.0" +
                "\nFOOD -100.0\nSHOPPING -200.0\nENTERTAINMENT -300.0", amList.printTotalList());
    }

    //EFFECTS: test the insufficient balance for add expense amount
    // add some expense amounts into AmountTrackerList, and
    // add an expense amount that larger than total amount in the AmountTrackerList
    // and test for it
    @Test
    public void testInsufficientBalance() {
        amList.setInitialAmount(2000);

        //EFFECTS: add some expense amounts into AmountTrackerList
        amList.addAmount(new AmountTracker("RENT", 1000));
        assertEquals(1000, amList.getTotalAmount());
        assertEquals(1000, amList.getExpenseAmount());
        assertEquals("RENT -1000.0", amList.printTotalList());
        amList.addAmount(new AmountTracker("OTHERS_EXPENSE", 100));
        assertEquals(900, amList.getTotalAmount());
        assertEquals(1100, amList.getExpenseAmount());
        assertEquals("RENT -1000.0\nOTHERS_EXPENSE -100.0", amList.printTotalList());
        amList.addAmount(new AmountTracker("FOOD", 100));
        assertEquals(800, amList.getTotalAmount());
        assertEquals(1200, amList.getExpenseAmount());
        assertEquals("RENT -1000.0\nOTHERS_EXPENSE -100.0\nFOOD -100.0", amList.printTotalList());

        //EFFECTS: add an expense amount that larger than total amount the addAmount
        // method should return false
        assertFalse(amList.addAmount(new AmountTracker("RENT", 1000)));
        amList.addAmount(new AmountTracker("", 100));
    }

}
