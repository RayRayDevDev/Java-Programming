import static java.lang.System.out;

public class PersonalLoan extends Loan {
    static double personalTotal;
    public PersonalLoan(int lnNum, String LstNm, double lnmt, int lnTerm, double prmRte) {
        super(lnNum, LstNm, lnmt, lnTerm);
        double finalDecimalValue = percentToDecimalCalc(prmRte);
        personalInterestRate = finalDecimalValue + 0.02;
        out.println("\nThe combined Personal Interest rate is: " + (personalInterestRate * 100) + "%.");
        personalTotal = personalLoanMath(lnmt);
        out.println("\nThe total amount due at the loan's maturity: $" + personalTotal);

    }

    public static double getPersonalInterestRate() {
        return personalInterestRate;
    }

    public static double getPersonalTotal() {
        return personalTotal;
    }

    public double personalLoanMath(double reqAmt) {
        return (reqAmt * personalInterestRate) + reqAmt;
    }

}