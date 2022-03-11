package simu.model;

import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * <p>Reittopas-rajapinnan DAO</p>
 * <p>Käytetään rajapinnasta hakemiseen</p>
 */
public class Reittiopas {

	private static Json Data = null;
	
	/**
	 * <p>Metodi matkan hakemiseen reittiopas-rajapinnasta</p>
	 * <p>getDistance(String nimi, double flat, double flon, double tlat, double tlon)</p>
	 * <p>nimi = Anna nimi matkalle esimerkiksi Dondotokannunkulma joka koodissa luodaan automaattisesti</p>
	 * <p>flat = from latitude leveysaste kohteesta </p>
	 * <p>flon = from longitude pituusaste	kohteesta</p>
	 * <p>tlat = to latitude leveysaste kohteeseen</p>
	 * <p>tlon = to longitude pituusaste kohteeseen</p>
	 * <p></p>
	 * <p>Näiden parametrejen täyttöjen jälkeen palauttaa metodi matka olion</p>
	 * 
	 */
	public static Matka getDistance(String nimi, double flat, double flon, double tlat, double tlon) {
		yhteys(flat, flon, tlat, tlon);
		double matka = Data.data.plan.itineraries.get(0).walkDistance;
		int aika = Data.data.plan.itineraries.get(0).duration;
		return new Matka(nimi, matka, aika);
	}
	/**
	 * <p>Metodi yhteyden muodostamiseen rajapintaan</p>
	 * <p>parametreissa annetaan yleisesti getDistance metodissa annetut coordinaatit</p>
	 * <p>metodi aloittaa operaation päivittääkseen instanssi muuttujassa olevan Json(nimi=data) olion</p>
	 * <p>Hakuun käytettävä koodikieli on GraphQL</p>
	 */
	public static void yhteys(double flat, double flon, double tlat, double tlon) {

		java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
				.uri(URI.create("https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql"))
				.timeout(Duration.ofMinutes(1)).header("Content-Type", "application/graphql")
				.POST(BodyPublishers.ofString("{ plan( from: {lat:" + flat + ",lon:" + flon + "} to:" + " {lat: " + tlat
						+ ", lon: " + tlon + "} numItineraries: 1 ) { itineraries { walkDistance duration } } }")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenAccept(Reittiopas::parse).join();
	}
	/**
	 * <p>Metodi parsee rajapinnasta olevan json datan ja muuttaa sen gson-kirjaston avulla Json olioon</p>
	 */
	public static void parse(String responseBody) {

		JsonParser parser = new JsonParser();
		JsonElement e = parser.parse(responseBody);
		Gson gson = new Gson();
		Data = gson.fromJson(e, Json.class);

	}

}
