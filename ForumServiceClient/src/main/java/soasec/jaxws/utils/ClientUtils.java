package soasec.jaxws.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.TimeZone;

public class ClientUtils {

	public static void main(String[] args){
		Date d1 = new Date(1628898240139L);
		Date d2 = new Date();
		System.out.println(computeDatesDiff(d1,d2));
		System.out.println(getTimeDiffasString(computeDatesDiff(d1,d2)));
		String res = "";
		
		
		LocalDate bday = d1.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate();
        LocalDate today = LocalDate.now();
        
        Period age = Period.between(bday, today);
        int years = age.getYears();
        int months = age.getMonths();
        
        System.out.println("number of years: " + years);
        System.out.println("number of months: " + months);

	}
	
	public static Map<ChronoUnit,Long> computeDatesDiff(Date date1, Date date2) {

	    long diffInMillies = date2.getTime() - date1.getTime();

	    //create the list
	    List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
	    Collections.reverse(units);

	    //create the result map of TimeUnit and difference
	    Map<TimeUnit,Long> time = new LinkedHashMap<TimeUnit,Long>();
	    long milliesRest = diffInMillies;

	    for (TimeUnit unit : units) {

	        //calculate difference in millisecond 
	        long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
	        long diffInMilliesForUnit = unit.toMillis(diff);
	        milliesRest = milliesRest - diffInMilliesForUnit;

	        //put the result in the map
	        time.put(unit,diff);
	    }
	    
	    
	    //creating variable to display result
        Map<ChronoUnit,Long> result = new LinkedHashMap<ChronoUnit,Long>();
        
        //check if is more than 30 days to display year/month/days format
		if(time.get(TimeUnit.DAYS) > 30) {
			LocalDate ld1 = date1.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate();
			LocalDate ld2 = date2.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate();
					
	        Period age = Period.between(ld1, ld2);
	        long years = age.getYears();
	        long months = age.getMonths();	
	        
	        result.put(ChronoUnit.YEARS, years);
	        result.put(ChronoUnit.MONTHS, months);
	        result.put(ChronoUnit.DAYS, time.get(TimeUnit.DAYS));
	        
		}
		else if (time.get(TimeUnit.DAYS) > 0){
		//else display the day/hour/minutes format
	        result.put(ChronoUnit.DAYS, time.get(TimeUnit.DAYS));	
	        result.put(ChronoUnit.HOURS, time.get(TimeUnit.HOURS));	
	        result.put(ChronoUnit.MINUTES, time.get(TimeUnit.MINUTES));	
		}
		else {
			//else display the hour/minutes/sec format	
	        result.put(ChronoUnit.HOURS, time.get(TimeUnit.HOURS));	
	        result.put(ChronoUnit.MINUTES, time.get(TimeUnit.MINUTES));	
	        result.put(ChronoUnit.SECONDS, time.get(TimeUnit.SECONDS));	
		}
		
	    return result;
	}
	
	
	
	
	public static String getTimeDiffasString(Map<ChronoUnit,Long> time) {
		String res = "";
			
		ArrayList<ChronoUnit> tu = new ArrayList<ChronoUnit>(Arrays.asList(ChronoUnit.values()));
		
		
			for (ChronoUnit t : ChronoUnit.values()) {
					if (time.get(t) != null && time.get(t) != 0) {					
								if (res != "")
									res = time.get(t) + " " + t.name().toLowerCase() + ", " + res;
								else
									res = time.get(t) + " " + t.name().toLowerCase() + res;
					}
			}
		
		//cleaning befour return the output
		res = res.replace("1 years", "1 year").replace("1 months", "1 month").replace("1 days", "1 day")
				.replace("1 hours", "1 hour").replace("1 minutes", "1 minute").replace("1 seconds", "1 second");
		
		return res;
	}
	
	public static Map<ChronoUnit,Long> computeTsDiff(long ts1, long ts2){
		Date d1 = new Date(ts1);
		Date d2 = new Date(ts2);
		return computeDatesDiff(d1, d2);
	}
	
}
