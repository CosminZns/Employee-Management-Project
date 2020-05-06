package members;

public class CompanyMember {
    private String fName;
    private String sName;
    private int socialSecurityNumber;

    public CompanyMember(String fname, String sname, int ssn)
    {
        this.fName = fname;
        this.sName = sname;
        this.socialSecurityNumber = ssn;
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName()
    {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
