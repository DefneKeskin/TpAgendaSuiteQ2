package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    
    public ChronoUnit frequency;
    public ArrayList<LocalDate> exceptions;
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        // TODO : implémenter cette méthode
        this.frequency = frequency;
        this.exceptions = new ArrayList<>();
        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        // TODO : implémenter cette méthode
        this.exceptions.add(date);
        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");  
        return this.frequency;
    }
    
    public boolean isInDay(LocalDate aDay) {
        super.isInDay(aDay);
        
        if (this.frequency == ChronoUnit.DAYS){
            return true;
        }
        if (super.isInDay(aDay)){
            return true;
        }else{
            LocalDate dayStart = myStart.plusDays(-1).toLocalDate();
            LocalDateTime myEnd = this.myStart.plus(this.myDuration);
            LocalDate dayEnd = myEnd.plusDays(1).toLocalDate();
            
            if (this.frequency == ChronoUnit.WEEKS){
            
                while (dayStart.isBefore(aDay)){
                    dayStart.plusWeeks(1);
                    dayEnd.plusWeeks(1);
                    if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)){
                        return true;
                    }
                }
                    
            }
            
            if (this.frequency == ChronoUnit.MONTHS){
                
                while (dayStart.isBefore(aDay)){
                    dayStart.plusMonths(1);
                    dayEnd.plusMonths(1);
                    if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)){
                        return true;
                    }
                }
                            
            }
            
            if (this.frequency == ChronoUnit.YEARS){
                while (dayStart.isBefore(aDay)){
                    dayStart.plusYears(1);
                    dayEnd.plusYears(1);
                    if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)){
                        return true;
                    }
                }
                            
            }
            
            return false;
                
        }
        
    }
    
    
    @Override
    public String toString() {
        return "RepetitiveEvent{" + "frequency=" + frequency + ", exceptions=" + exceptions + '}';
    }
    

}
