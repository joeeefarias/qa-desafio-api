package br.com.restassuredapitesting.tests.booking.requests.payloads;

import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class BookingPayloads {

    public static JSONObject validPayloadBooking(){
            JSONObject payload = new JSONObject();
            JSONObject bookingDates = new JSONObject();

            bookingDates.put("checkin", "2018-01-01");
            bookingDates.put("checkout", "2019-01-01");

            payload.put("firstname", "John");
            payload.put("lastname", "Constantine");
            payload.put("totalprice", 111);
            payload.put("depositpaid", true);
            payload.put("bookingdates", bookingDates);
            payload.put("additionalneeds", "Breakfast");

            return payload;
    }

        public static JSONObject openFieldsPayload(String nome, String sobrenome, String checkin, String checkout){
                JSONObject payload = new JSONObject();
                JSONObject bookingDates = new JSONObject();

                bookingDates.put("checkin", checkin);
                bookingDates.put("checkout", checkout);

                payload.put("firstname", nome);
                payload.put("lastname", sobrenome);
                payload.put("totalprice", 111);
                payload.put("depositpaid", true);
                payload.put("bookingdates", bookingDates);
                payload.put("additionalneeds", "");

                if (nome.equals("")){
                        return payload.put("firstname", "Mario");
                }
                if (sobrenome.equals("")){
                        return payload.put("lastname", "Bros");
                }
                if (checkin.equals("")){
                        return bookingDates.put("checkin", "2022-02-23");
                }
                if (checkout.equals("")){
                        return bookingDates.put("checkout", "2022-06");
                }

                return payload;
        }

}
