import org.junit.Test;
import static org.junit.Assert.*;

class Testing {
    @Test
    public void testStatement() {
        Customer johannes = new Customer("Johannes");

        Movie wallstreet_movie  = new Movie("The Wall Street Wolf", 0);
        Movie montypython_movie = new Movie("Flying Circus", 1);
        Movie titanic_movie     = new Movie("Titanic for Kids", 2);

        Rental titanic_rental     = new Rental(titanic_movie, 1);
        Rental wallstreet_rental  = new Rental(wallstreet_movie, 2);
        Rental montypython_rental = new Rental(montypython_movie, 3);


        assertEquals(johannes.statement()
                     , "Rental Record for Johannes\n"
                     + "Amount owed is 0.0\n"
                     + "You earned 0 frequent renter points");

        johannes.addRental(titanic_rental);
        assertEquals(johannes.statement()
                     , "Rental Record for Johannes\n"
                     + "\tTitanic For Kids\t1.5\n"
                     + "Amount owed is 1.5\n"
                     + "You earned 1 frequent renter points");

        johannes.addRental(wallstreet_rental);
        assertEquals(johannes.statement()
                     , "Rental Record for Johannes\n"
                     + "\tTitanic For Kids\t1.5\n"
                     + "\tThe Wall Street Wolf\t2.0\n"
                     + "Amount owed is 3.5\n"
                     + "You earned 2 frequent renter points");

        johannes.addRental(montypython_rental);
        assertEquals(johannes.statement()
                     , "Rental Record for Johannes\n"
                     + "\tTitanic For Kids\t1.5\n"
                     + "\tThe Wall Street Wolf\t2.0\n"
                     + "\tFlying Circus\t9.0\n"
                     + "Amount owed is 12.5\n"
                     + "You earned 4 frequent renter points");
    }
}
