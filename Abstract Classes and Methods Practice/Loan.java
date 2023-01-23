import static java.lang.System.out;

public abstract class Loan implements LoanConstants {
    int loanNumber = 0;
    String lastName = null;  //Customer's Last Name
    double loanAmt = 0;  //Requested Loan Amount
    static double personalInterestRate = PersonalLoan.getPersonalInterestRate();  //Personal Loan Combined Interest Rate in decimal form
    static double businessInterestRate = BusinessLoan.getBusinessInterestRate();  //Business Loan Combined Interest Rate in decimal form
    int loanTerm;  //Requested Loan Term
    static String isBusiness = null;
    public Loan(int lnNum, String LstNm, double lnmt, int lnTerm) { //"Ln" = "Loan."
        lnNum += 1;
        loanNumber = lnNum;
        lastName= LstNm;
        loanAmt = lnmt;
        loanTerm = lnTerm;
}
    
    public static double percentToDecimalCalc(double userInputtedPercentage) {
        double finalDecimalValue = userInputtedPercentage / 100;
        return finalDecimalValue;
    }

     

    public String toString() {
        String loanInfo = "Loan Number: " + loanNumber + ".\n" + 
        "Last Name: " + lastName + ".\n" + 
        "Requested Loan Amount: $" + loanAmt + ".\n" + 
        "Loan Term: " + loanTerm + " year(s).\n";
        return loanInfo;
    }

}