package com.ningzeta.deviceOUI.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ningzeta.deviceOUI.domains.OUI;
import com.ningzeta.deviceOUI.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Services for the device details operations.
 *
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */
@Service
public class DeviceServices {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Get the Device Information.
	 * @param oui
	 * @return
	 */
	public OUI getDeviceByOUI(String oui) {
		Object obj = this.redisRepository.getDeviceByOUI(oui);
		return this.objectMapper.convertValue(obj,OUI.class);
	}

	public Object getDeviceByVendor(String vendor) {
		Object obj = this.redisRepository.getDeviceByVendor(vendor);
		return obj;
	}

	/**
	 * Process the IEEE OUI List and store to Redis.
	 * @return Object[]
	 */
	public Object[] processIEEEOUI() {
		ArrayList<OUI> ouiArrayList = this.readIEEEOUI();
		HashMap<String, OUI> ouiHashMap = new HashMap<>();
		HashMap<String, ArrayList<OUI>> vendorHashMap = new HashMap<>();
		for (OUI oui : ouiArrayList) {
			ouiHashMap.put(oui.getOui(),oui);
			String vendor = oui.getVendor();
			if (vendorHashMap.containsKey(vendor)) {
				vendorHashMap.get(vendor).add(oui);
			}
			else {
				ArrayList<OUI> localOui = new ArrayList<>();
				localOui.add(oui);
				vendorHashMap.put(vendor, localOui);
			}
		}

		this.redisRepository.save(ouiHashMap,vendorHashMap);
		return new Object[] {ouiHashMap, vendorHashMap};
	}

	/**
	 * Get the lastest IEEE OUI list.
	 * @return ArrayList<OUI>
	 */
	private ArrayList<OUI> readIEEEOUI() {
		ArrayList<OUI> ouiList = new ArrayList<>();
		try {
			System.setProperty("http.proxyHost", "global.proxy.alcatel-lucent.com");
			System.setProperty("http.proxyPort", "8000");
			URL ouiURL = new URL(OUI_URL);
			Scanner scanner = new Scanner(ouiURL.openStream());
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(line.contains("(hex)")) {
					String[] splits = line.split("\\(hex\\)");
					ouiList.add(new OUI(splits[0].trim(),splits[1].trim()));
				}
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			System.clearProperty("http.proxyHost");
			System.clearProperty("http.proxyPort");
			return ouiList;
		}

	}

	private static String OUI_URL = "http://standards-oui.ieee.org/oui/oui.txt";

}
