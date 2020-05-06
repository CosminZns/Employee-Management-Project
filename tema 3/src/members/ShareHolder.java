package members;

public class ShareHolder extends CompanyMember{

    private double ownedPercentage;

    public ShareHolder(String fname, String sname, int ssn, double ownedPercentage) {
        super(fname, sname, ssn);
        this.ownedPercentage = ownedPercentage;
    }

    public void setOwnedPercentage(double ownedPercentage) {
        this.ownedPercentage = ownedPercentage;
    }

    public double getOwnedPercentage() {
        return ownedPercentage;
    }
}
