package simu.model;

import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Reittiopas {
	
	private static Json Data = null;
	
	public static Matka getDistance(String nimi,double flat,double flon,double tlat,double tlon) {
		yhteys(flat,flon,tlat,tlon);
		double matka = Data.data.plan.itineraries.get(0).walkDistance;
		int aika = Data.data.plan.itineraries.get(0).duration;
		return new Matka(nimi,matka,aika);
	}

	public static void yhteys(double flat,double flon,double tlat,double tlon){
		
		java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
			      .uri(URI.create("https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql"))
			      .timeout(Duration.ofMinutes(1))
			      .header("Content-Type", "application/graphql")
			      .POST(BodyPublishers.ofString("{ plan( from: {lat:" + flat + ",lon:" + flon +"} to:"
			      +" {lat: " + tlat + ", lon: " + tlon + "} numItineraries: 1 ) { itineraries { walkDistance duration } } }"))
			      .build();	
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
		.thenApply(HttpResponse::body)
		.thenAccept(Reittiopas::parse)
		.join();
	}
	
	
	public static void parse(String responseBody) {
		
		JsonParser parser = new JsonParser();
		JsonElement a = parser.parse(responseBody);
		Gson gson = new Gson();
		Data = gson.fromJson(a, Json.class);
		
	}

}
