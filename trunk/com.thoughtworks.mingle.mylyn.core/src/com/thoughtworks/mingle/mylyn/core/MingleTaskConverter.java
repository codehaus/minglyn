package com.thoughtworks.mingle.mylyn.core;

import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author Ketan Padegaonkar
 */
public class MingleTaskConverter implements Converter {

	private final String	serverUrl;

	public MingleTaskConverter(URL serverUrl) {
		this(serverUrl.toString());
	}

	public MingleTaskConverter(String url) {
		this.serverUrl = url;
	}

	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		MingleTask mingleTask = (MingleTask) source;

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.putAll(mingleTask.attributes);
		hashMap.put("id", mingleTask.getTaskId());
		hashMap.put("name", mingleTask.getSummary());
		hashMap.put("description", mingleTask.getDescription());

		Set<Entry<String, String>> entrySet = hashMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			writer.startNode(entry.getKey());
			writer.setValue(entry.getValue());
			writer.endNode();
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		HashMap<String, String> map = new HashMap<String, String>();

		while (reader.hasMoreChildren()) {
			try {
				reader.moveDown();
				String nodeName = reader.getNodeName();
				String value = reader.getValue();
				reader.moveUp();

				map.put(nodeName, value);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		return new MingleTask(serverUrl, map.get("id"), map.get("name"), map.get("description"), map);
	}

	public boolean canConvert(Class type) {
		return type.equals(MingleTask.class);
	}

}
