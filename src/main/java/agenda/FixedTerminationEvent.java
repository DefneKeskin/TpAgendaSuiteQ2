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
        this.terminationInclusive = terminationInclusive;

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
     * @param numberOfOccurrences the number of occurrences of this repetitive
     * event
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
        if (this.terminationInclusive != null) {
            return this.terminationInclusive;
        } else {
            //notre pb c'est qu'il ajoute 1 semaine de plus, en faite il debute pas directement ?? myStart
            //Ainsi une des solutions possibles est qu'on peut reduire le nombre d'occurence de 1 
            return this.myStart.plus(this.numberOfOccurrences-1, frequency).toLocalDate();
        }
       
    }

    public long getNumberOfOccurrences() {
        if (this.terminationInclusive != null) {
            //calculer le nombre d'occurence ?? partir de la date de fin et de la date de debut
            LocalDate dayStart = myStart.plusDays(-1).toLocalDate();
            //long daysBetween = frequency.between(LocalDate.parse("2016-08-31"),LocalDate.parse("2016-11-30"));
            //Duration dur = Duration.between(dayStart, terminationInclusive,);
            long occurence = frequency.between(dayStart,terminationInclusive);
            //ajoute +1 car on compte la derni??re semaine/mois/jours aussi 
            return occurence+1;
        } else {
            return numberOfOccurrences;
        }
    }

    public boolean isInDay(LocalDate aDay) {

        if (this.exceptions.contains(aDay)) {
            return false;
        } else {

            if (this.frequency == ChronoUnit.DAYS) {
                return true;
            }

            if (super.isInDay(aDay)) {
                return true;
            } else {
                LocalDate dayStart = myStart.plusDays(-1).toLocalDate();
                LocalDateTime myEnd = this.myStart.plus(this.myDuration);
                LocalDate dayEnd = myEnd.plusDays(1).toLocalDate();

                if (this.terminationInclusive != null) {

                    while (dayStart.isBefore(this.getTerminationDate())) {
                        dayStart = dayStart.plus(1, frequency);
                        dayEnd = dayStart.plus(1, frequency);
                        if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)) {
                            return true;
                        }
                    }
                } else {
                    for (int i = 1; i <= this.getNumberOfOccurrences(); i++) {
                        dayStart = dayStart.plus(1, frequency);
                        dayEnd = dayStart.plus(1, frequency);
                        if (aDay.isAfter(dayStart) && aDay.isBefore(dayEnd)) {
                            return true;
                        }
                    }

                }

                return false;

            }
        }
    }

    @Override
    public String toString() {
        return "FixedTerminationEvent{" + "terminationInclusive=" + terminationInclusive + ", numberOfOccurrences=" + numberOfOccurrences + '}';
    }

}
