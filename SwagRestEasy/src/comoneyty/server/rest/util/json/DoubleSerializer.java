package comoneyty.server.rest.util.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleSerializer extends JsonSerializer<Double> {
	public static DecimalFormat formatter = new DecimalFormat("0.00");
	
    @Override
    public void serialize(Double value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {
    	formatter.setRoundingMode( RoundingMode.DOWN );
    	String s = formatter.format(value).replace(',', '.');
    	jgen.writeNumber(new BigDecimal(s));
        //jgen.writeString(s.replace(',', '.'));
    }
}