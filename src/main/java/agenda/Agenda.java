package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    //
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
        for (Event e : this.listeEvenements) {
            if (e.isInDay(day)) {
                evenementDay.add(e);
            }
        }
        return evenementDay;
    }

    @Override
    public String toString() {
        return "Agenda{" + "listeEvenements=" + listeEvenements + '}';
    }

    //PARTIE 2 
    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     *
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {

        ArrayList<Event> eventTitle = new ArrayList<>();
        //si le titre existe déjà alors on l'ajoute dans la nouvelle liste 
        for (Event e : this.listeEvenements) {
            if (e.getTitle() == title) {
                eventTitle.add(e);
            }
        }
        return eventTitle;
    }

    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     *
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     *
     * 
     */
    public boolean isFreeFor(Event e) { 
        for (Event event : this.listeEvenements) {
            for (LocalDateTime d = e.myStart; d.isBefore(e.myStart.plus(e.myDuration).plusDays(1)); d = d.plusDays(1)) {
                //regarde s'il y  a un chevauchement on ajoute donc pour verifier cela 1 jour à d et on verifie 
                //Attention au dernier jour il n'est pas fini mais au dernier jour +1 oui 
                if (event.isInDay(d.toLocalDate())) {
                    return false;
                }
            }
        }
        return true;
    }
}
