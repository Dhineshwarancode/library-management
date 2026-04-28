public class FinePolicy{
    private double ratePerDay;      
    private double maxFine;         
    private int gracePeriod;        
    
    public FinePolicy(double ratePerDay, double maxFine, int gracePeriod){
        this.ratePerDay = ratePerDay;
        this.maxFine = maxFine;
        this.gracePeriod = gracePeriod;
    }
    
    public double getRatePerDay(){
        return ratePerDay;
    }
    
    public void setRatePerDay(double ratePerDay){
        this.ratePerDay = ratePerDay;
    }
    
    public double getMaxFine(){
        return maxFine;
    }
    
    public void setMaxFine(double maxFine){
        this.maxFine = maxFine;
    }
    
    public int getGracePeriod(){
        return gracePeriod;
    }
    
    public void setGracePeriod(int gracePeriod){
        this.gracePeriod = gracePeriod;
    }
    

    public double computeFine(long daysOverdue){
        // 0 days overdue: No fine
        if(daysOverdue <= 0){
            return 0.0;
        }
        
        double fine = 0.0;
        
        if(daysOverdue <= 7){
            fine = daysOverdue * 2.0;
        } else if(daysOverdue <= 15){
            
            fine = 7*2.0 + (daysOverdue-7) * 5.0;
        } else {
            
            fine = 7*2.0 + 8*5.0+(daysOverdue - 15)* 10.0;
        }
        
        
        if(fine > 500.0){
            fine = 500.0;
        }
        
        return fine;
    }
    
    @Override
    public String toString(){
        return "FinePolicy{" +
                "Tiered Rates: 0 days=₹0, 1-7 days=₹2/day, 8-15 days=₹5/day, 16+ days=₹10/day" +
                ", Maximum Fine=₹500" +
                '}';
    }
}
