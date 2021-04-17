package me.hobrin.autojsonencoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonWorker {
	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			if (args.length > 1) {
				JsonArray array = new JsonArray();
				for (int i = 0; i < args.length; i++) {
					String s = doJson(args[i], false);
					array.add(s);
				}
				System.out.println(array.toString());
			} else {
				//1 argument
				doJson(args[0], true);
			}

		} else {
			while (true) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				  
		        // Reading data using readLine
		        String json = reader.readLine();
				doJson(json, true);
			}
		}
	}
	public static String doJson(String s, boolean print) {
		JsonObject json = new Gson().fromJson(s, JsonObject.class);
		
		String res = new JsonWorker(json).work();
		if (print)
			System.out.println(res);
		return res;
	}
	
	private static List<String> usedNames = new ArrayList<>();
	
	public JsonObject json;
	
	public final String mainName; //gets initialized in work
	
	
	public JsonWorker(JsonObject json) {
		this.json = json;
		this.mainName = "json";
	}
	private JsonWorker(JsonObject json, String name) {
		this.json = json;
		this.mainName = name;
	}
	public String work() {
		Code code = new Code();
		
		code.addLine("JsonObject "+mainName+" = new JsonObject();\n");
		
		Set<Map.Entry<String, JsonElement>> entries = json.entrySet();
		for (Map.Entry<String, JsonElement> entry: entries) {
			
			String name = entry.getKey();
			//will only execute if name is taken
			for (int i = 0; usedNames.contains(name); i++) {
				name = entry.getKey() + i;
			}
			usedNames.add(name);
			
			
			JsonElement element = entry.getValue();
			
			ElementCode elCode = handleElement(name, element);
			code.addLines(elCode.getCode());
			code.addLine(mainName+".add(\""+name+"\", " + elCode.varName+");");
			
			code.addLine();
		}
		return code.getCode();
	}
	private ElementCode handleElement(String name, JsonElement element) {
		ElementCode code = new ElementCode(name);
		
		if (element.isJsonObject()) {
			code.addLine(new JsonWorker(element.getAsJsonObject(), name).work());
			return code;
		}
		if (element.isJsonArray()) {
			JsonArray array = element.getAsJsonArray();
			
			code.addLine("JsonArray " + name + " = new JsonArray();\n");
			
			for (int i = 0; i < array.size(); i++) {
				JsonElement el = array.get(i);
				ElementCode elCode = this.handleElement(name + "_" + i, el);
				code.addLines(elCode.getCode());
				
				code.addLine(name+".add("+elCode.getVarName()+");");
			}
			code.addLine();
			return code;
		}
		if (element.isJsonPrimitive()) {
			JsonPrimitive prim = element.getAsJsonPrimitive();
			code.addLine("JsonPrimitive " + name + " = new JsonPrimitive(" + prim.toString() + ");");
			return code;
		}
		if (element.isJsonNull()) {
			code.addLine("JsonNull " + name + " = new JsonNull();");
		}
		
		return null;
	}
	public String getRNGName() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
