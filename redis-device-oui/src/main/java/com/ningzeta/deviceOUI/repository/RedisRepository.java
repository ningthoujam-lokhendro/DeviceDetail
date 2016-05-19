package com.ningzeta.deviceOUI.repository;

import com.ningzeta.deviceOUI.domains.OUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */
@Repository
public class RedisRepository {

	@Autowired
	private RedisTemplate redisTemplate;

	public Object getDeviceByOUI(String oui) {
		return redisTemplate.boundHashOps("OUI").get(oui);
	}

	public Object getDeviceByVendor(String vendor) {
		return this.redisTemplate.boundHashOps("VENDOR").get(vendor);
	}

	public void save(HashMap<String, OUI> ouiHashMap, HashMap<String, ArrayList<OUI>> vendorHashMap) {
		this.redisTemplate.boundHashOps("OUI").putAll(ouiHashMap);
		this.redisTemplate.boundHashOps("VENDOR").putAll(vendorHashMap);
	}

}
