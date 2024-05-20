package tss;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Theatre {
    //Always use the same input time foramt
    final private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    public String name = "";
    public HashSet<Artist> registeredArtists = new HashSet<Artist>();
    public ArrayList<Performance> performancesAvailable = new ArrayList<Performance>();

    //    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    public Theatre(String name) {
        name = name;
    }

    public Boolean registerArtist(Artist artist) {
        System.out.println(String.format("Registered %s", artist.artistName));
        return registeredArtists.add(artist);
    }

    public Boolean registerPerformance(Performance performance) {
        System.out.println(String.format("Registered %s doing a performance at %s", performance.performanceArtist, performance.performanceStart));
        boolean c1 = this.checkPerformanceNewDay(performance);
        boolean c2 = this.checkPerformanceOverlaps(performance);
        boolean c3 = registeredArtists.contains(performance.performanceArtist);
        if (c1 && !c2 && c3) {
            return performancesAvailable.add(performance);
        } else {
            System.out.println("Could not add due to overlap in timing or performance goes into the next day, or artist not registered!");
            return false;
        }

    }

    /**
     * @param performance
     * @return true if performance is one the same day
     */
    public Boolean checkPerformanceNewDay(Performance performance) {
        String startDate = getDateFromISOString(performance.performanceStart);
        String endDate = getDateFromISOString(getPerformanceEnd(performance.performanceStart, performance.performanceDuration));
        return startDate.equals(endDate);
    }

    public Boolean checkPerformanceOverlaps(Performance newPerformance) {
        boolean flag = false;
        String newPerformanceStart_Date = getDateFromISOString(newPerformance.performanceStart);
//        String newPerformanceStart_Time = getTimeFromIsoString(newPerformance.performanceStart);
//        String newPerformanceEnd = getPerformanceEnd(newPerformance.performanceStart, newPerformance.performanceDuration);
//        String newPerformanceEnd_Time = getTimeFromIsoString(newPerformanceEnd);

        for (Performance p : performancesAvailable) {

            String pStart_date = getDateFromISOString(p.performanceStart);
//            String pStart_time = getTimeFromIsoString(p.performanceStart);
//            String pEnd = getPerformanceEnd(p.performanceStart, p.performanceDuration);
//            String pEnd_time = getTimeFromIsoString(pEnd);

            // Check that the new performance startTime must be after performance EndTime
            if (newPerformanceStart_Date.equals(pStart_date) && performanceWithinAnother(p, newPerformance)) {
                flag = true;
            }
            // Performance endTime must be before all other performance startTimes
        }
        return flag;
    }

    /**
     * @param performanceStart
     * @param performanceDuration
     * @return the end time of the performance in ISO 8601 format (i.e in yyyyMMdd'T'HHmm)
     */
    private String getPerformanceEnd(String performanceStart, int performanceDuration) {
        LocalDateTime startTime = LocalDateTime.parse(performanceStart, formatter);
//        String startFormat = startTime.format(formatter);//For debugging only
        LocalDateTime endTime = startTime.plusMinutes(performanceDuration);
        String endTimeString = endTime.format(formatter);
        return endTimeString;
    }

    /**
     * @param p1
     * @param p2
     * @return true if p2 is within p1
     */
    private boolean performanceWithinAnother(Performance p1, Performance p2) {
        LocalDateTime p1_ldt_start = LocalDateTime.parse(p1.performanceStart, formatter);
        LocalDateTime p1_ldt_end = LocalDateTime.parse(getPerformanceEnd(p1.performanceStart, p1.performanceDuration), formatter);
        LocalDateTime p2_ldt_start = LocalDateTime.parse(p2.performanceStart, formatter);
        LocalDateTime p2_ldt_end = LocalDateTime.parse(getPerformanceEnd(p2.performanceStart, p2.performanceDuration), formatter);

        boolean flag = false;
        if(p1_ldt_start.isEqual(p2_ldt_start))
        {
            flag = true;
        }
        if ((p2_ldt_end.isAfter(p1_ldt_start)) && (p2_ldt_end.isBefore(p1_ldt_end))) {
            flag = true;
        }
        if ((p2_ldt_start.isAfter(p1_ldt_start)) && (p2_ldt_start.isBefore(p1_ldt_end))) {
            flag = true;
        }
        return flag;
    }

    public String getDateFromISOString(String isoDate) {
        LocalDateTime date_Iso = LocalDateTime.parse(isoDate, formatter);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
        String Date = date_Iso.format(format);
        return Date;
    }

    public String getTimeFromIsoString(String isoDate) {
        LocalDateTime date_Iso = LocalDateTime.parse(isoDate, formatter);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        String Date = date_Iso.format(format);
        return Date;
    }

    public void printStatusOfTheatre() {
        System.out.println("Registered artists: ");
        for (Artist a : registeredArtists) {
            System.out.println(String.format("%s", a.artistName));
        }
        System.out.println("Performances here: ");
        for (Performance p : performancesAvailable) {
            System.out.println(String.format("%s performing at %s for %d minutes", p.performanceArtist.toString(), p.performanceStart, p.performanceDuration));
        }
    }
}
