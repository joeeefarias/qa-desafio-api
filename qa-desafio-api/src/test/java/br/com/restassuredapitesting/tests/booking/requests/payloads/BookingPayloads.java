package br.com.restassuredapitesting.tests.booking.requests.payloads;

import org.json.JSONObject;

public class BookingPayloads {


        public static JSONObject openFieldsValidPayload(String firstname, String lastname, String checkin, String checkout){
                JSONObject payload = new JSONObject();
                JSONObject bookingDates = new JSONObject();

                bookingDates.put("checkin", checkin);
                bookingDates.put("checkout", checkout);

                payload.put("firstname", firstname);
                payload.put("lastname", lastname);
                payload.put("totalprice", 2544);
                payload.put("depositpaid", true);
                payload.put("bookingdates", bookingDates);
                payload.put("additionalneeds", "Edredom");

                if (firstname.isEmpty()){
                         payload.put("firstname", "Hajime");
                }
                if (lastname.isEmpty()){
                         payload.put("lastname", "Ippo");
                }
                if (checkin.isEmpty()){
                         bookingDates.put("checkin", "2022-02-23");
                }
                if (checkout.isEmpty()){
                         bookingDates.put("checkout", "2022-06-18");
                }

                return payload;
        }



}
