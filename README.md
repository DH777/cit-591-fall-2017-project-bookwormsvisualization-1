# bookworm-vitialization

## Write at the very beginning
1. Leaflet-interactive-map.html is built on "http://leafletjs.com/examples/choropleth/example.html". The html file is modified to accommodate our project.
2. The GeoJSON with state shapes (us-states.js) was kindly shared by Mike Bostock of D3 fame, extended with density values from this Wikipedia article based on July 1st 2011 data from US Census Bureau and assigned to statesData JS variable.
3. org.json-20120521.jar and twitter4j-core-4.0.4.jar should be added to the Referenced libraries.
4. Please create a class called Secret with codes as follows.
package twitter;
public class Secret {
	public static final String cKey = "Please enter your Twitter key";
	public static final String cSecret = "Please enter your Twitter Secret";
}

## Project Description
This project can help you bookworm find your fellow bookworms! Do you want to find out where your fellow bookworms live so that you can also move to a closer place? There might be more bookclubs if there are more bookworms. You could even open your own bookclubs! How exciting!
![screen shot 2017-12-13 at 6 06 50 pm](https://user-images.githubusercontent.com/6525504/34066806-4325076e-e1e3-11e7-8069-ae84c9a5c6b8.png)

## Project Design - CRC
