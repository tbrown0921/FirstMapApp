package com.example.firstmapapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class NetworkingClient {

    final static String BASE_URL = "https://savewithdiscounthealthcare.com/api/api/ProviderSearchJSON";
    final static String POSTS_String = "?Lat=33.125387&Lon=-96.786024&Radius=60&Cat=5";

    public static void getPost() {
        try {
            URL postURL = new URL(BASE_URL + POSTS_String);
            GetPostsAsyncTask task = new GetPostsAsyncTask();
            task.execute(postURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static class GetPostsAsyncTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];
            String results = null;
            try {
                results = getResponseFromHTTPURL(url);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Location[] Locations = null;

            JSONObject[] jsonObjectArray = getJsonObjectArray(getJsonArray(s));

            ArrayList<Search> searches = new ArrayList<Search>();


            for (JSONObject search: jsonObjectArray) {

                try {

                    JSONObject[] jsonLocationObjectArray = getJsonObjectArray(search.getJSONArray("Locations"));

                    Locations = new Location[jsonLocationObjectArray.length];

                    for (int l = 0; l < jsonLocationObjectArray.length; l++) {

                        JSONObject location = jsonLocationObjectArray[l];

                        double longitude = location.getDouble("longitude");
                        double latitude = location.getDouble("longitude");
                        int catId = location.getInt("catid");
                        double distance = location.getDouble("distance");


                        JSONObject[] jsonProviderObjectArray = getJsonObjectArray(location.getJSONArray("t2"));

                        Provider[] Providers = new Provider[jsonProviderObjectArray.length];

                        for (int i = 0; i < jsonProviderObjectArray.length; i++) {
                            JSONObject provider = jsonProviderObjectArray[i];
                            int id = provider.getInt("providerid");
                            String name = provider.getString("providername").trim();
                            String practice = provider.getString("primarypractice").trim();
                            String phone = provider.getString("Phone").trim();
                            String address1 = provider.getString("Address1").trim();
                            String address2 = provider.getString("Address2").trim();
                            String city = provider.getString("City").trim();
                            String state = provider.getString("State").trim();
                            String zip = provider.getString("Zip").trim();
                            int status = provider.getInt("Status");
                            String feeSchedule = provider.getString("FeeSchedule").trim();
                            String office = provider.getString("Office").trim();
                            int catValue = provider.getInt("catvalue");
                            Provider newProvider = new Provider(id, name, practice, phone, address1, address2, city, state, zip, status, feeSchedule, office, catValue);
                            Providers[i] = newProvider;
                        }

                        Location newLocation = new Location(longitude, latitude, catId, distance, Providers);
                        Locations[l] = newLocation;
                   //     System.out.println(Arrays.toString(Locations));
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                int j = 0;

           //         Search newSearch = new Search(
           //                 search.getDouble("Lat"),
           //                 search.getDouble("Long"),
            //                search.getInt("Cat"),
            //                search.getJSONArray("locations")
             //           );
              //          searches.add(newSearch);
            //   } catch (JSONException e)
            //    {
            //        e.printStackTrace();
           //     }
            }

        //    System.out.println(posts.size());
        //    System.out.println(posts);
        }
    }


    public static String getResponseFromHTTPURL(URL url) throws IOException {

        String server_response = "";
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server_response;
      /*
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
     //   urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type","application/json");
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else
            {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

       */
    }

    //Convert InputStream to String
    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null){
                response.append(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    private static JSONArray getJsonArray(String response) {
        JSONArray array = null;
        try {
            array = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    private static JSONObject[] getJsonObjectArray(JSONArray array) {
        JSONObject[] jsonObjectArray = new JSONObject[array.length()];
        for (int i = 0; i < (array != null ? array.length() : 0); i++){
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                jsonObjectArray[i] = jsonObject;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectArray;
    }

 //   public interface PostReceivedListener {
  //      void didReceivePost(Array<Post> post);

  //  }

}
