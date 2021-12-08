package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    
    ArrayList<Event> listeEvenements;

    public Agenda() {
        this.listeEvenements = new ArrayList<>();
    }
    
    
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        this.listeEvenements.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        ArrayList<Event> evenementDay = new ArrayList<>();
        for (Event e : this.listeEvenements){
            if (e.isInDay(day)){
                evenementDay.add(e);
            }
        }
        return evenementDay;
    }
}
