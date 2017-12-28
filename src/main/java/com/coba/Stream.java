package com.coba;

import twitter4j.FilterQuery;
import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
	public static void main(String[] args) {
		final String consumerKey = "JfrTZBwjVwvPZnicaO1hVgSlv";
		final String consumerSecret = "ExZ3vH66yArt2CQn34g1jN2AzkOPHpGKNBD8JAdilFnGWjWUVT";
		final String accessToken = "934631846263926784-dvamNL08pcfyJqMWQAa4yQMXmFNyJ4g";
		final String accessTokenSecret = "3ktzepDOsiHacAmzxtAevB7EH1UU6JIYq9wr7qyv7Jq8A";

		String[] keywords = { "jokowi"};

		FilterQuery qry = new FilterQuery();
		qry.track(keywords);

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();

		StatusListener listener2 = null;
		twitterStream.addListener(listener2);
		twitterStream.addListener(new StatusListener() {

			public void onException(Exception arg0) {
				System.out.println(arg0);
				
			}

			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onStatus(Status status) {
				
				// TODO Auto-generated method stub
				//JSONObject value = new JSONObject(status);
				//System.out.println("from -> " + status.getUser().getName());
				System.out.println(status.getText());
			}

			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
			}});
		twitterStream.filter(qry);
	}
}
	
