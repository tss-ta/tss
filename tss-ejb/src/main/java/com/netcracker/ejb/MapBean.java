/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import java.io.Reader;

/**
 *
 * @author Виктор
 */
public class MapBean implements SessionBean {

    private final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";
    private final static String proxy = "http://anonymouse.org/cgi-bin/anon-www.cgi/";

    public String geodecodeAddress(double lng, double lat) throws JSONException, IOException {
        Map<String, String> params = Maps.newHashMap();
        params.put("language", "en");
        params.put("sensor", "false");
        params.put("latlng", Double.toString(lng) + "," + Double.toString(lat));
        String url = baseUrl + '?' + encodeParams(params);
        JSONObject response = read(url);
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        String formattedAddress = location.getString("formatted_address");
        return formattedAddress;
    }

    public double[] geocodeAddress(String address) throws JSONException, IOException {
        Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("address", address);
        String url = baseUrl + '?' + encodeParams(params);
        JSONObject response = read(url);
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        double lng = location.getDouble("lng");// долгота
        double lat = location.getDouble("lat");// широта
        double[] coordinates = new double[2];
        coordinates[0] = lng;
        coordinates[1] = lat;
        return coordinates;
    }

    public float calculateDistance(String addrFrom, String addrTo) throws IOException, JSONException {
    	sleep(800);
        String sourceUrl = proxy + "http://maps.googleapis.com/maps/api/directions/json";
        Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "en");
        params.put("mode", "driving");
        params.put("origin", addrFrom);
        params.put("destination", addrTo);
        final String url = sourceUrl + '?' + encodeParams(params);
        final JSONObject response = read(url);
        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        int distance = location.getJSONObject("distance").getInt("value");
        return (float) distance / 1000;
    }

    private void sleep(int i) {
    	try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }

    private JSONObject read(final String url) throws IOException, JSONException {
        final InputStream is = new URL(url).openStream();
        try {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            final JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
    }

}
