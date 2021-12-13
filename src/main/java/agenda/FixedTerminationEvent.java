package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {
    public LocalDate terminationInclusive;
    public long numberOfOccurrences;
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
         super(title, start, duration, frequency);
         this.terminationInclusive=terminationInclusive;

    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.numberOfOccurrences = numberOfOccurrences;
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
       return  terminationInclusive;
    }

    public long getNumberOfOccurrences() {
        return numberOfOccurrences;
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
            
                while (dayStart.isBefore(this.terminationInclusive)){
                    dayStart.plusWeeks(1);
                    dayEnd.plusWeeks(1);
                    if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)){
                        return true;
                    }
                }
                    
            }
            
            if (this.frequency == ChronoUnit.MONTHS){
                
                while (dayStart.isBefore(this.terminationInclusive)){
                    dayStart.plusMonths(1);
                    dayEnd.plusMonths(1);
                    if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)){
                        return true;
                    }
                }
                            
            }
            
            if (this.frequency == ChronoUnit.YEARS){
                while (dayStart.isBefore(this.terminationInclusive)){
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
        return "FixedTerminationEvent{" + "terminationInclusive=" + terminationInclusive + ", numberOfOccurrences=" + numberOfOccurrences + '}';
    }
    
    
        
}
