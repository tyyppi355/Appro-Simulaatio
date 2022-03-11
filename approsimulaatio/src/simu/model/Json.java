package simu.model;

import java.util.List;

/**
 * <p>Json-luokka</p>
 * <p>tämä luokka on tehty vain gson kirjaston objektin luontia varten</p>
 * <p>Lisätietoa tulevasta datasta: https://digitransit.fi/en/developers/apis/1-routing-api/itinerary-planning/</p>
 */
public class Json {
	
	Data data;
	public class Data {
		
		Plan plan;
		public class Plan {
			
			List<Itineraries> itineraries;
			public class Itineraries {
				double walkDistance;
				int duration;
				
				public Itineraries(double walkDistance,int duration) {
					this.walkDistance = walkDistance;
					this.duration = duration;
				}
			}

		}

	}

}