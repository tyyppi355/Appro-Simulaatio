package simu.model;

import java.util.List;

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