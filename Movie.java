public class Movie {

  public static final int  CHILDRENS = 2;
  public static final int  REGULAR = 0;
  public static final int  NEW_RELEASE = 1;

  private String _title;
  private int _priceCode;

  public Movie(String title, int priceCode) {
      _title = title;
      _priceCode = priceCode;
  }

  public int getPriceCode() {
      return _priceCode;
  }

  public void setPriceCode(int arg) {
     _priceCode = arg;
  }

  public String getTitle (){
      return _title;
  };
}

class Rental {
    private Movie _movie;
    private int _daysRented;

    int getFrequentPoints(){
        if((getMovie.getPriceCode() == Movie.NEW_RELEASE) && getDaysRented > 1)
            return 2;
        else
            return 1;
    }

    double getCharge(){
        double result = 0;
        switch (getMovie().getPriceCode()) {
        case Movie.REGULAR:
          result += 2;
          if (getDaysRented() > 2)
             result += (getDaysRented() - 2) * 1.5;
          break;
        case Movie.NEW_RELEASE:
          result += getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          result += 1.5;
          if (getDaysRented() > 3)
             result += (getDaysRented() - 3) * 1.5;
          break;
        }
        return result;
    }

    public Rental(Movie movie, int daysRented) {
      _movie = movie;
      _daysRented = daysRented;
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

   // public double amountFor(Rental aRental){
   //     return aRental.getCharge();
   // }

  public String statement() {
     double totalAmount = 0;
     int frequentRenterPoints = 0;
     Enumeration rentals = _rentals.elements();
     String result = "Rental Record for " + getName() + "\n";
     while (rentals.hasMoreElements()) {
        //double thisAmount = 0;
        Rental each = (Rental) rentals.nextElement();

        frequentRenterPoints  += each.getFrequentPoints();

        // add bonus for a two day new release rental
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
            each.getDaysRented() > 1) frequentRenterPoints ++;

        //show figures for this rental
        result += "\t" + each.getMovie().getTitle()+ "\t" +
            String.valueOf(thisAmount) + "\n";
        totalAmount += each.getCharge();;

     }
     //add footer lines
     result +=  "Amount owed is " + String.valueOf(totalAmount) + "\n";
     result += "You earned " + String.valueOf(frequentRenterPoints) +
             " frequent renter points";
     return result;
}
