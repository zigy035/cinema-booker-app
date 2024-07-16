package com.cinema.booker.exception;

public class ShowtimeOverlapException extends RuntimeException {

    public ShowtimeOverlapException(long theatreId, String showTime) {
        super("Showtime at " + showTime + " overlaps with an existing showtime in theatre " + theatreId);
    }
}
