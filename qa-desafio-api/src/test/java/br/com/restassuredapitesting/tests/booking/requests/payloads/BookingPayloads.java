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
                         payload.put("firstname", "Makunouchi");
                }
                if (lastname.isEmpty()){
                         payload.put("lastname", "Ippo");
                }
                if (checkin.isEmpty()){
                         bookingDates.put("checkin", "2022-06-01");
                }
                if (checkout.isEmpty()){
                         bookingDates.put("checkout", "2022-07-30");
                }

                return payload;
        }

        public static JSONObject invalidPayload(String firstname){
                JSONObject payload = new JSONObject();

                payload.put("firstname", firstname);

                return payload;
        }

        public static JSONObject extraFieldsValidPayload(){
                JSONObject payload = new JSONObject();
                JSONObject bookingDates = new JSONObject();

                bookingDates.put("checkin", "2022-12-01");
                bookingDates.put("checkout", "2023-01-05");

                payload.put("firstname", "Son");
                payload.put("lastname", "Goku");
                payload.put("totalprice", 2544);
                payload.put("depositpaid", true);
                payload.put("bookingdates", bookingDates);
                payload.put("roomnumber", 18);
                payload.put("kingsizebed", true);
                payload.put("airconditioning", false);
                payload.put("additionalneeds", "Edredom");


                return payload;
        }



}
