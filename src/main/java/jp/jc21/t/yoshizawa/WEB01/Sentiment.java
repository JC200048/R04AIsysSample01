package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Sentiment1 message = getLanguage("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("Negative:" + message.documents[0].confidenceScores.negative);
			System.out.println("Neutal:" + message.documents[0].confidenceScores.neutral);
			System.out.println("Positive:" + message.documents[0].confidenceScores.positive);
		}
	}

	static Sentiment1 getLanguage(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3b03-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "543fa637f6f442ac8a53a3a57804c512");

		Docs1 doc = new Docs1();
		doc.id = "1";
		doc.text = s;

		Source1 src = new Source1();
		src.documents = new Docs1[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		Sentiment1 message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Sentiment1.class);
			reader.close();
		}
		return message;
	}

}

class Sentiment1 {
	Documents1[] documents;
	String[] errors;
	String modelVersion;
}


class Documents1 {
	ConfidenceScores1 confidenceScores;
	String id;
	Sentences[] sentences;
	String sentiment;
	String[] warnings;
}

class ConfidenceScores1 {
	float negative;
	float neutral;
	float positive;
}

class Sentences {
	Targets[] targets;
	ConfidenceScores2 confidenceScores2;
	int length;
	int offset;
	Assessments[] assessments;
	String sentiment;
	String text;
}

class Targets {
	ConfidenceScores3 confidenceScores3;
	int length;
	int offset;
	Relations[] relations;
	String sentiment;
	String text;
}

class ConfidenceScores3 {
	float negative;
	float positive;
}

class Relations {
	String ref;
	String relationtype;
}

class ConfidenceScores2 {
	float negative;
	float neutral;
	float positive;
}

class Assessments {
	ConfidenceScores4 confidenceScores4;
	boolean isNegated;
	int length;
	int offset;
	String sentiment;
	String text;
}

class ConfidenceScores4 {
	float negative;
	float positive;
}

class Source1 {
	Docs1[] documents;
}

class Docs1 {
	String id;
	String text;
}
