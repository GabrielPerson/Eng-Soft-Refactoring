import java.util.Vector;
import java.util.Enumeration;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Testing.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

public class Movie {

  public static final int  CHILDRENS = 2;
  public static final int  REGULAR = 0;
  public static final int  NEW_RELEASE = 1;

  private String _title;
  private Price _price;

  public Movie(String name, int priceCode) {
      _title = name;
      setPriceCode(priceCode);
  }

  public int getPriceCode() {
      return _price.getPriceCode();
  }

  public void setPriceCode(int arg) {
      switch (arg) {
          case REGULAR:
             _price = new RegularPrice();
             break;
          case CHILDRENS:
             _price = new ChildrensPrice();
             break;
          case NEW_RELEASE:
             _price = new NewReleasePrice();
             break;
          default:
             throw new IllegalArgumentException("Incorrect Price Code");
       }
  }

  public String getTitle (){
      return _title;
  };

  double getCharge(int daysRented) {
      return _price.getCharge(daysRented);
  }

  int getFrequentRenterPoints(int daysRented) {
      return _price.getFrequentRenterPoints(daysRented);
  }
}

class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
      _movie = movie;
      _daysRented = daysRented;
    }

    int getFrequentRenterPoints(){
        return _movie.getFrequentRenterPoints(_daysRented);
    }

    double getCharge(){
        return _movie.getCharge(_daysRented);
    }

    public int getDaysRented() {
      return _daysRented;
    }

    public Movie getMovie() {
      return _movie;
    }
}

class Customer {
   private String _name;
   private Vector _rentals = new Vector();

   public Customer (String name){
      _name = name;
   };

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }
   public String getName (){
      return _name;
   };


    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
          Rental each = (Rental) rentals.nextElement();
          result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
           Rental each = (Rental) rentals.nextElement();
           result += each.getFrequentRenterPoints();
        }
        return result;
    }

  public String statement() {
     double totalAmount = 0;
     int frequentRenterPoints = 0;
     Enumeration rentals = _rentals.elements();
     String result = "Rental Record for " + getName() + "\n";
     while (rentals.hasMoreElements()) {
        //double thisAmount = 0;
        Rental each = (Rental) rentals.nextElement();

        //show figures for this rental
        result += "\t" + each.getMovie().getTitle()+ "\t" +
            String.valueOf(each.getCharge()) + "\n";


     }

    public String htmlStatement(){
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            // show figures for each rental
            result += each.getMovie().getTitle()+ ": " +
            String.valueOf(each.getCharge()) + "<BR>\n";
        }

        // add footer lines
        result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" +
              String.valueOf(getTotalFrequentRenterPoints()) +
              "</EM> frequent renter points<P>";
        return result;
    }

     //add footer lines
     result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
     result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
             " frequent renter points";
     return result;
}

abstract class Price {
   abstract int getPriceCode();

   abstract double getCharge(int daysRented);

   int getFrequentRenterPoints(int daysRented) {
       return 1;
   }
}

class ChildrensPrice extends Price {
   int getPriceCode() {
       return Movie.CHILDRENS;
   }
   double getCharge(int daysRented) {
     double result = 1.5;
     if (daysRented > 3)
         result += (daysRented - 3) * 1.5;
     return result;
   }
}

class NewReleasePrice extends Price {
   int getPriceCode() {
       return Movie.NEW_RELEASE;
   }
   double getCharge(int daysRented){
       return daysRented * 3;
    }

    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2: 1;
    }
}

class RegularPrice extends Price {
   int getPriceCode() {
       return Movie.REGULAR;
   }
   double getCharge(int daysRented) {
       double result = 2;
       if (daysRented > 2)
           result += (daysRented - 2) * 1.5;
       return result;
   }
}
