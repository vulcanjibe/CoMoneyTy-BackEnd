package comoneyty.server.rest.util.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import comoneyty.server.rest.util.Utilitaire;

public class DateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		String dateAsString = p.getText();
		try {
			if (dateAsString.length() == 8)

				return Utilitaire.FORMAT_DATE_COURT.parse(dateAsString);

			else if (dateAsString.length() == 10)
				return Utilitaire.FORMAT_DATE_STANDARD.parse(dateAsString);
			else
				return Utilitaire.FORMAT_DATE.parse(dateAsString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}